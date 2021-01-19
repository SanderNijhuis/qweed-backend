package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.jpa.WeedperiodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

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
        if (weedperiod.isPresent()){
            //weedperiod = calculateStats(weedperiod);
        }
        
        return weedperiod.orElse(null);
    }

    @Override
    public Weedperiod calculateStats(Weedperiod weedperiod) {
        weedperiod.setCostPerJoint(weedperiod.getCostPerGram() * weedperiod.getAverageGramPerJoint());
        return weedperiod;
    }

    @Override
    public Weedperiod findByCustomer(Customer customer) {
        Optional<Weedperiod> weedperiod = weedperiodRepository.findWeedperiodByCustomerAndIsInitial(customer, true);
        return weedperiod.orElse(null);
    }

    @Override
    public Weedperiod save(Weedperiod weedperiod) {
        if(weedperiod.getIsInitial()) {
            if (weedperiod.getName() == null || weedperiod.getCustomerName() == null || weedperiod.getCustomer() == null || weedperiod.getEndDate() == null || weedperiod.getStartDate() == null || weedperiod.getIsInitial() == null || weedperiod.getAverageDurationPerWeek() == null || weedperiod.getAverageGramPerJoint() == null || weedperiod.getAverageJointsSmokedPerWeek() == null || weedperiod.getCostPerGram() == null){
                return null;
            }

            if(weedperiod.getStartDate().after(weedperiod.getEndDate())){
                return null;
            }
            if(weedperiod.getEndDate().after(new Date())){
                return null;
            }

            Weedperiod queried_weedperiod = findByCustomer(weedperiod.getCustomer());

            if (queried_weedperiod != null)
                return null;
        } else {
            Weedperiod queried_weedperiod = findByCustomer(weedperiod.getCustomer());
            if (weedperiod.getName() == null || weedperiod.getCustomerName() == null || weedperiod.getCustomer() == null || weedperiod.getStartDate() == null || weedperiod.getIsInitial() == null || weedperiod.getAverageGramPerJoint() == null || weedperiod.getCostPerGram() == null){
                return null;
            }
            if(weedperiod.getStartDate().after(new Date())){
                return null;
            }
            if (queried_weedperiod == null)
                return null;
        }

        return weedperiodRepository.save(weedperiod);
    }
}
