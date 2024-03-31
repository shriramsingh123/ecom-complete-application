package com.shriram.ecom.services.customer.review;

import com.shriram.ecom.dto.OrderedProductsResponseDto;
import com.shriram.ecom.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
