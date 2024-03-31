package com.shriram.ecom.services.cart;

import com.shriram.ecom.dto.AddProductInCartDto;
import com.shriram.ecom.dto.OrderDto;
import com.shriram.ecom.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);
    OrderDto applyCoupon(Long userId, String code);
    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPlacedOrders(Long userId);

    OrderDto searchOrderByTrackingId(UUID trackingId);

}
