package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.Weedperiod;

public interface WeedperiodService {
    void deleteByID(long id);

    Weedperiod findByID(Long id);

    Weedperiod findByCustomer(Customer customer);

    Weedperiod save(Weedperiod weedperiod);
}
