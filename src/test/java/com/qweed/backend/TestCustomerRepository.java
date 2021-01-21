package com.qweed.backend;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCustomerRepository implements CustomerRepository {
    private List<Customer> customers = new ArrayList<>();
    @Override
    public Optional<Customer> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByToken(String token) {
        for (Customer customer:customers ) {
            if(customer.getToken() == token){
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findCustomerByUserName(String username) {
        if(customers != null) {

            for (Customer customer : customers) {
                if (customer.getUserName() == username) {
                    return Optional.of(customer);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public <S extends Customer> S save(S entity) {
        customers.add(entity);
        return entity;
    }

    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Customer> findAll() {
        return customers;
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Customer entity) {
        customers.remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
