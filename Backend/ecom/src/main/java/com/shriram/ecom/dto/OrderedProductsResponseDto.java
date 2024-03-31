package com.shriram.ecom.dto;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.List;

@Data
public class OrderedProductsResponseDto {

    private List<ProductDto> productDtoList;

    private Long orderAmount;


}
