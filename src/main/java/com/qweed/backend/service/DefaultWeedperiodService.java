package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.jpa.WeedperiodRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("weedperiodService")
public class DefaultWeedperiodService implements WeedperiodService {
    @Autowired
    WeedperiodRepository weedperiodRepository;

    @Override
    public void deleteByID(long id) {
        weedperiodRepository.deleteById(id);
    }

    @Override
    public Weedperiod findByID(Long id) {
        Optional<Weedperiod> weedperiod = weedperiodRepository.findWeedperiodById(id);
        return weedperiod.orElse(null);
    }

    @Override
    public Weedperiod save(Weedperiod weedperiod) {
        Weedperiod queried_weedperiod = findByID(weedperiod.getId());
        if(queried_weedperiod != null)
            return null;
        return weedperiodRepository.save(weedperiod);
    }
}
