package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface CustomerService {
    void deleteById(Long id);

    Optional<User> findByToken(String token);

    Iterable<Customer> findAll();

    Customer findById(Long id);

    String login(String username, String password);

    Customer save(Customer customer);
}
