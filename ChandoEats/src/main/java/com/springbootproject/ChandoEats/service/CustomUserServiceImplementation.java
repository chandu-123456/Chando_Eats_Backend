package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Domain.USER_ROLE;
import com.springbootproject.ChandoEats.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {
    public UserRepository userRepository;

    public CustomUserServiceImplementation(UserRepository userRepository) {
        this.userRepository=userRepository;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        com.springbootproject.ChandoEats.model.User user = userRepository.findByEmail(username);

        if(user==null) {
            throw new UsernameNotFoundException("user not found with email  - "+username);
        }

        USER_ROLE role=user.getRole();

        if(role==null) role=USER_ROLE.ROLE_CUSTOMER;

        System.out.println("role  ---- "+role);

        List<GrantedAuthority> authorities=new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
