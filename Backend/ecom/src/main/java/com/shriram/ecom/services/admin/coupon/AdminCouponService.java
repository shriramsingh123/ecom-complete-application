package com.shriram.ecom.services.admin.coupon;

import com.shriram.ecom.entity.Coupon;

import java.util.List;

public interface AdminCouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
