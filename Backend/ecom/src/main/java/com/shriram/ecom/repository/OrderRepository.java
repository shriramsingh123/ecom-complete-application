package com.shriram.ecom.repository;

import com.shriram.ecom.entity.Order;
import com.shriram.ecom.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order,Long>{
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findByUserIdAndOrderStatusIn(Long userId,List<OrderStatus> orderStatus);

    Optional<Order> findByTrackingId(UUID trackingId);

    List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);

    Long countByOrderStatus(OrderStatus status);
}
