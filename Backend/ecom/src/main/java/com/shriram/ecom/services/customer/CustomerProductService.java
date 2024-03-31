package com.shriram.ecom.services.customer;

import com.shriram.ecom.dto.ProductDetailDto;
import com.shriram.ecom.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> searchProductByTitle(String title);

    List<ProductDto> getAllProducts();

    ProductDetailDto getProductDetailById(Long productId);

}
