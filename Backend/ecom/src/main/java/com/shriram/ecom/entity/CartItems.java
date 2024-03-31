package com.shriram.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shriram.ecom.dto.CartsItemDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore //
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore //
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore //
    private Order order;

    public CartsItemDto getCartDto(){
        CartsItemDto cartsItemDto = new CartsItemDto();
        cartsItemDto.setId(id);
        cartsItemDto.setPrice(price);
        cartsItemDto.setProductId(product.getId());
        cartsItemDto.setQuantity(quantity);
        cartsItemDto.setUserId(user.getId());
        cartsItemDto.setProductName(product.getName());
        cartsItemDto.setReturnedImg(product.getImg());

        return cartsItemDto;
    }
}
