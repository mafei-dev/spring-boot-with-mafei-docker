package com.example.stockmanagementservice.service;

import com.example.stockmanagementservice.entity.CartEntity;
import com.example.stockmanagementservice.entity.CartItemEntity;
import com.example.stockmanagementservice.entity.ItemEntity;
import com.example.stockmanagementservice.exception.ItemNotFoundException;
import com.example.stockmanagementservice.exception.NoCartItemException;
import com.example.stockmanagementservice.grpc.*;
import com.example.stockmanagementservice.repository.CartItemRepository;
import com.example.stockmanagementservice.repository.CartRepository;
import com.example.stockmanagementservice.repository.ItemRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;

@GrpcService
@AllArgsConstructor
@Slf4j
public class CartService extends CartServiceGrpc.CartServiceImplBase {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public void addCartItem(AddCartItemRequest request, StreamObserver<AddCartItemResponse> responseObserver) {
        log.debug("addCartItem:request {}", request);
        //get the cart of the given user.
        CartEntity cartEntity = this.cartRepository
                .findByUsername(request.getUsername())
                .orElseGet(() -> {
                    //if the user has no a cart. (the user is new) > add new cart for the user.
                    return this.cartRepository.save(
                            CartEntity.builder()
                                    .username(request.getUsername())
                                    .build()
                    );
                });
        //check the item is valid or not.[check the item code from the database.]
        ItemEntity itemEntity = this.itemRepository
                .findById(request.getItemCode())
                .orElseThrow(() -> new ItemNotFoundException(
                        "The item is not available in the system. itemCode:" + request.getItemCode(),
                        request.getItemCode()
                ));

        //save the cart item in to the user's cart.
        this.cartItemRepository.save(
                CartItemEntity.builder()
                        .cartItemId(null)
                        .itemCode(request.getItemCode())
                        .itemName(request.getItemName())
                        .qty(request.getQty())
                        .total(request.getQty() * itemEntity.getUnitPrice())
                        .cartId(cartEntity.getCartId())
                        .build()
        );
        responseObserver.onNext(
                AddCartItemResponse
                        .newBuilder()
                        .setIsSuccess(true)
                        .build()
        );
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     * @throws NoCartItemException if there is no cart for the user.
     */
    @Override
    public void getCartDetail(GetCartItemDetailRequest request, StreamObserver<GetCartItemDetailResponse> responseObserver) {
        log.debug("getCartDetail:username : {}", request.getUsername());
        //check the cart exist or not.
        CartEntity cartEntity = this.cartRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new NoCartItemException(
                        "The user's cart is not started yet. username:" + request.getUsername(),
                        request.getUsername()
                ));
        GetCartItemDetailResponse.Builder newBuilder = GetCartItemDetailResponse.newBuilder();
        newBuilder.setUsername(cartEntity.getUsername());
        newBuilder.setCartId(cartEntity.getCartId());

        //get the cart items from the cart_item table.
        this.cartItemRepository.findAllByCartId(cartEntity.getCartId())
                .forEach(cartItemEntity -> {
                    newBuilder.addCartItems(
                            CartItemDto
                                    .newBuilder()
                                    .setCartItemId(cartItemEntity.getCartItemId())
                                    .setItemCode(cartItemEntity.getItemCode())
                                    .setItemName(cartItemEntity.getItemName())
                                    .setQty(cartItemEntity.getQty())
                                    .setTotal(cartItemEntity.getTotal())
                                    .build()
                    );
                });
        responseObserver.onNext(newBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void removeItem(RemoveCartItemRequest request, StreamObserver<RemoveCartItemResponse> responseObserver) {
        //find the cart detail for the given user.
        this.cartRepository
                .findByUsername(request.getUsername())
                .ifPresent(cartEntity -> {
                    //remove all the items regarding the cart (cart_item)
                    this.cartItemRepository.deleteAllByCartId(cartEntity.getCartId());
                });
        responseObserver.onNext(
                RemoveCartItemResponse.newBuilder()
                        .setIsSuccess(true)
                        .build()
        );
        responseObserver.onCompleted();
    }
}
