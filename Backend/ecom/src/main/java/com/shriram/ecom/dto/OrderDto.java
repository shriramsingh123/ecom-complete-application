package com.shriram.ecom.dto;

import com.shriram.ecom.entity.CartItems;
import com.shriram.ecom.entity.User;
import com.shriram.ecom.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {


    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    private String userName;

    private List<CartsItemDto> cartItems;


    private String couponName;
}
