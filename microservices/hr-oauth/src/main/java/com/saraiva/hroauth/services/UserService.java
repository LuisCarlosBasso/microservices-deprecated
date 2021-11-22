package com.saraiva.hroauth.services;

import com.saraiva.hroauth.entities.User;
import com.saraiva.hroauth.feignClients.UserFeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserFeignClient feignClient;

    public UserService(UserFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public User findByEmail(String email) {
        User user = feignClient.findByEmail(email).getBody();
        if (user == null) {
            throw new IllegalArgumentException("Email not found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = feignClient.findByEmail(email).getBody();
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }
}
