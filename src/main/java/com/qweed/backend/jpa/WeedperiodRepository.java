package com.qweed.backend.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeedperiodRepository extends CrudRepository<Weedperiod, Long> {
    Optional<Weedperiod> findWeedperiodByCustomerAndIsInitial(Customer customer, Boolean isInitial);

    Optional<Weedperiod> findWeedperiodById(Long id);
}
