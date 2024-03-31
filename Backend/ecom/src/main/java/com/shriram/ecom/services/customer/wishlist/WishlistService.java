package com.shriram.ecom.services.customer.wishlist;

import com.shriram.ecom.repository.WishlistDto;

import java.util.List;

public interface WishlistService {

    WishlistDto addProductToWishlist(WishlistDto wishlistDto);
    List<WishlistDto> getWishListByUserId(Long userId);
}
