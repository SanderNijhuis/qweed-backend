package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.Weedperiod;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface WeedperiodService {

    void deleteByID(long id);

    Weedperiod findByID(Long id);

    Weedperiod save(Weedperiod weedperiod);
}
