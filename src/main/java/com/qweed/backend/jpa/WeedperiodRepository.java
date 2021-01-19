package com.qweed.backend.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeedperiodRepository extends CrudRepository<Weedperiod, Long> {
    Optional<Weedperiod> findWeedperiodByCustomer(Customer customer);

    Optional<Weedperiod> findWeedperiodById(Long id);
}
