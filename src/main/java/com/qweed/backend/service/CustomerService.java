package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface CustomerService {
    String login(String username, String password);

    Optional<User> findByToken(String token);

    Iterable<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);
}
