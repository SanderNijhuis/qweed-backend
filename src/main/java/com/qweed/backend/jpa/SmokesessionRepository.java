package com.qweed.backend.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmokesessionRepository extends CrudRepository<Smokesession, Long> {
    //Optional<Weedperiod> findWeedperiodByCustomer(Customer customer);

    Optional<Smokesession> findSmokesessionById(Long id);
}
