package com.qweed.backend;

import com.qweed.backend.jpa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestSmokesessionRepository implements SmokesessionRepository {
    private List<Smokesession> smokesessions = new ArrayList<>();;

    @Override
    public Optional<Smokesession> findSmokesessionById(Long id) {
        for (Smokesession smokesession:smokesessions ) {
            if(smokesession.getId() == id){
                return Optional.of(smokesession);
            }
        }
        return Optional.empty();
    }


    @Override
    public <S extends Smokesession> S save(S entity) {
        smokesessions.add(entity);
        return entity;
    }

    @Override
    public <S extends Smokesession> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Smokesession> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Smokesession> findAll() {
        return null;
    }

    @Override
    public Iterable<Smokesession> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        for (Smokesession smokesession:smokesessions ) {
            if(smokesession.getId() == aLong){
                smokesessions.remove(findSmokesessionById(aLong));
            }
        }
    }

    @Override
    public void delete(Smokesession entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Smokesession> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
