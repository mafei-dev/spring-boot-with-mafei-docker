package org.example.service;

import com.example.userpointservice.service.IncreaseUserPointRequest;
import com.example.userpointservice.service.IncreaseUserPointResponse;
import com.example.userpointservice.service.UserPointServiceGrpc;
import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import io.grpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.entity.UserPointEntity;
import org.example.entity.UserPointLogDetailEntity;
import org.example.exception.PointNotSufficientException;
import org.example.exception.UserPointUpdateFailedException;
import org.example.repository.UserPointLogDetailRepository;
import org.example.repository.UserPointRepository;
import org.example.types.UpdateState;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@GrpcService
@AllArgsConstructor
@Slf4j
public class UserPointService extends UserPointServiceGrpc.UserPointServiceImplBase {
    private final UserPointRepository userPointRepository;
    private final UserPointLogDetailRepository userPointLogDetailRepository;

    @Override
    @Transactional
    public void increasePoint(IncreaseUserPointRequest request, StreamObserver<IncreaseUserPointResponse> responseObserver) {
        log.debug("increasePoint:request:{}",request);
        try {
            //business logic
            Optional<UserPointEntity> user = this.userPointRepository.findByUsername(request.getUsername());
            user.ifPresent(userPointEntity -> {
                //01-update the user point.
                int updatedValue = userPointEntity.getPoints() + request.getAmount();
                int userPointUpdated = this.userPointRepository.updateUserPoint(
                        updatedValue,
                        userPointEntity.getUsername()
                );
                if (userPointUpdated == 0) {
                    UserPointUpdateFailedException exception = new UserPointUpdateFailedException("The update is failed.");
                    //throw the error.
                    //method-1
                    //                responseObserver.onError(exception);
                    //method-2
                    //                responseObserver.onError(Status.INTERNAL.asRuntimeException());
                    //method-3
                    com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                            .setCode(Code.DATA_LOSS.getNumber())
                            .setMessage(exception.getMessage())
                            .addDetails(Any.pack(
                                    ErrorInfo.newBuilder()
                                            .setReason("not enough point balance")
                                            .putMetadata("username", request.getUsername())
                                            .build()
                            ))
                            .build();
                    responseObserver.onError(StatusProto.toStatusException(status));
                    return;
                }
                //02-add new point history
                this.userPointLogDetailRepository.save(
                        UserPointLogDetailEntity
                                .builder()
                                .userPointId(userPointEntity.getId())
                                .reason(request.getReason())
                                .changedBy(request.getAmount())
                                .updateState(request.getAmount() > 0 ? UpdateState.INCREASED : UpdateState.DECREASED)
                                .updatedTime(LocalDateTime.now())
                                .build()
                );
                //send the response.
                responseObserver.onNext(IncreaseUserPointResponse
                        .newBuilder()
                        .setLastAmount(updatedValue)
                        .build()
                );
                responseObserver.onCompleted();

            });
            if (!user.isPresent()) {

                //the user doesn't exist and also the updated point is minus.

                if (request.getAmount() < 1) {
                    PointNotSufficientException exception = new PointNotSufficientException("The user has not enough amount of point to deduct.");
                    com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                            .setCode(Code.DATA_LOSS.getNumber())
                            .setMessage(exception.getMessage())
                            .addDetails(Any.pack(
                                    ErrorInfo.newBuilder()
                                            .setReason("The user is new.")
                                            .putMetadata("username", request.getUsername())
                                            .build()
                            ))
                            .build();
                    responseObserver.onError(StatusProto.toStatusException(status));
                    return;
                }
                //01-add new user with initial point
                UserPointEntity userPointEntity = this.userPointRepository.save(
                        UserPointEntity
                                .builder()
                                .username(request.getUsername())
                                .points(request.getAmount())
                                .build()
                );
                //02-add new point history
                this.userPointLogDetailRepository.save(UserPointLogDetailEntity
                        .builder()
                        .userPointId(userPointEntity.getId())
                        .reason(request.getReason())
                        .changedBy(request.getAmount())
                        .updateState(request.getAmount() > 0 ? UpdateState.INCREASED : UpdateState.DECREASED)
                        .updatedTime(LocalDateTime.now())
                        .build()
                );
                responseObserver.onNext(IncreaseUserPointResponse.newBuilder()
                        .setLastAmount(request.getAmount())
                        .build());
                responseObserver.onCompleted();
            }
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }
}
