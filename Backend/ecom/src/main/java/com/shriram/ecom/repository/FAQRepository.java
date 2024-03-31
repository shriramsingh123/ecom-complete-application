package com.shriram.ecom.repository;

import com.shriram.ecom.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQRepository extends JpaRepository<FAQ,Long> {

    List<FAQ> findAllByProductId(Long productId);
}
