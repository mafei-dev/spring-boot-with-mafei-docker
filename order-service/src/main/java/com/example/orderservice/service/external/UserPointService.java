package com.example.orderservice.service.external;

import com.example.userpointservice.service.*;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserPointService {

    @GrpcClient("user-point-service")
    private UserPointServiceGrpc.UserPointServiceBlockingStub userPointServiceBlockingStub;

    public void increasePoint(IncreaseUserPointRequest request) {
        IncreaseUserPointResponse increaseUserPointResponse = this.userPointServiceBlockingStub.increasePoint(request);
        int lastAmount = increaseUserPointResponse.getLastAmount();
        log.debug("lastAmount : {}", lastAmount);
    }

    public void increasePointRevert(IncreaseUserPointRevertRequest request) {
            IncreaseUserPointRevertResponse increaseUserPointRevertResponse = this.userPointServiceBlockingStub.increasePointRevert(request);
            int lastAmount = increaseUserPointRevertResponse.getLastAmount();
            log.debug("lastAmount : {}", lastAmount);
    }


}
