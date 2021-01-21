package com.qweed.backend;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.jpa.WeedperiodRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestWeedperiodRepository implements WeedperiodRepository {
    private List<Weedperiod> weedperiods = new ArrayList<>();;

    @Override
    public Optional<Weedperiod> findWeedperiodByCustomerAndIsInitial(Customer customer, Boolean isInitial) {
        for (Weedperiod weedperiod:weedperiods ) {
            if(weedperiod.getCustomer() == customer && weedperiod.getIsInitial() == isInitial){
                return Optional.of(weedperiod);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Weedperiod> findWeedperiodById(Long id) {
        for (Weedperiod weedperiod:weedperiods ) {
            if(weedperiod.getId() == id){
                return Optional.of(weedperiod);
            }
        }
        return Optional.empty();
    }

    @Override
    public <S extends Weedperiod> S save(S entity) {
        weedperiods.add(entity);
        return entity;
    }

    @Override
    public <S extends Weedperiod> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Weedperiod> findById(Long aLong) {
        for (Weedperiod weedperiod:weedperiods ) {
            if(weedperiod.getId() == aLong){
                return Optional.of(weedperiod);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Weedperiod> findAll() {
        return weedperiods;
    }

    @Override
    public Iterable<Weedperiod> findAllById(Iterable<Long> longs) {
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
    public void delete(Weedperiod entity) {
        weedperiods.remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Weedperiod> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
