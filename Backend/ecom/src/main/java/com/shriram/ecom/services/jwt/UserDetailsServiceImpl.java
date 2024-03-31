package com.shriram.ecom.services.jwt;

import com.shriram.ecom.entity.User;
import com.shriram.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findFirstByEmail(username);
        if (optionalUser.isEmpty())throw new UsernameNotFoundException("Username Not Found",null);
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),
                optionalUser.get().getPassword(),new ArrayList<>());
    }

}
