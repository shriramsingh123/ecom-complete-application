package com.shriram.ecom.services.adminOrder;

import com.shriram.ecom.dto.AnalyticsResponse;
import com.shriram.ecom.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();
    OrderDto changeOrderStatus(Long orderId , String status);
    AnalyticsResponse calculateAnalytics();
}
