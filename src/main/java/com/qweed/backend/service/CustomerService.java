package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface CustomerService {
    void deleteByUserName(String userName);

    Iterable<Customer> findAll();

    Optional<User> findByToken(String token);

    Customer findByUserName(String userName);

    Customer getOverview(String userName);

    String login(String username, String password);

    Customer save(Customer customer);
}
