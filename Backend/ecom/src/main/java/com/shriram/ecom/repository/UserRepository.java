package com.shriram.ecom.repository;

import com.shriram.ecom.entity.User;
import com.shriram.ecom.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String username);

    User findByRole(UserRole userRole);
}
