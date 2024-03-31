package com.shriram.ecom.repository;

import com.shriram.ecom.entity.FAQ;
import com.shriram.ecom.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProductId(Long productId);

}
