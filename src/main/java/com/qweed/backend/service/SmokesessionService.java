package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.Smokesession;
import com.qweed.backend.jpa.Weedperiod;

public interface SmokesessionService {
    void deleteByID(long id);

    Smokesession findByID(Long id);

    Smokesession save(Smokesession smokesession);
}
