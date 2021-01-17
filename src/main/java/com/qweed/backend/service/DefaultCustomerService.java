package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("customerService")
public class DefaultCustomerService implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void deleteByUserName(String userName) {
        customerRepository.delete(findByUserName(userName));
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<User> findByToken(String token) {
        Optional<Customer> customer = customerRepository.findByToken(token);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            User user = new User(customer1.getUserName(), customer1.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Customer findByUserName(String userName) {
        Optional<Customer> customer = customerRepository.findCustomerByUserName(userName);
        return customer.orElse(null);
    }

    @Override
    public String login(String username, String password) {
        Optional<Customer> customer = customerRepository.login(username, password);
        if (customer.isPresent()) {
            String token = UUID.randomUUID().toString();
            Customer custom = customer.get();
            custom.setToken(token);
            customerRepository.save(custom);
            return token;
        }

        return StringUtils.EMPTY;
    }

    @Override
    public Customer save(Customer customer) {
        Customer queried_customer = findByUserName(customer.getUserName());
        if(queried_customer != null)
            return null;

        return customerRepository.save(customer);
    }
}
