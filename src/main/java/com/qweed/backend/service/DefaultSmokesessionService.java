package com.qweed.backend.service;

import com.qweed.backend.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("smokesessionService")
public class DefaultSmokesessionService implements SmokesessionService {
    @Autowired
    SmokesessionRepository smokesessionRepository;

    @Autowired
    private WeedperiodService weedperiodService;

    @Autowired
    public DefaultSmokesessionService(SmokesessionRepository smokesessionRepository, WeedperiodService weedperiodService) {
        this.smokesessionRepository = smokesessionRepository;
        this.weedperiodService = weedperiodService;
    }

    @Override
    public void deleteByID(long id) {
        smokesessionRepository.deleteById(id);
    }

    @Override
    public Smokesession findByID(Long id) {
        Optional<Smokesession> smokesession = smokesessionRepository.findSmokesessionById(id);
        return smokesession.orElse(null);
    }

    @Override
    public Smokesession save(Smokesession smokesession) {
        if (smokesession.getWeedperiod().getIsInitial()){
            return null;
        }
        if (smokesession.getName() == null || smokesession.getWeedperiod() == null || smokesession.getStartDate() == null ||  smokesession.getJointsSmoked() == null||  smokesession.getDuration() == null){
            return null;
        }
        if(smokesession.getStartDate().after(new Date())){
            return null;
        }
        if(smokesession.getStartDate().before(smokesession.getWeedperiod().getStartDate())){
            return null;
        }
        if (smokesession.getStartDate().after(smokesession.getWeedperiod().getEndDate())) {
            Weedperiod weedperiod = smokesession.getWeedperiod();
            weedperiod.setEndDate(smokesession.getStartDate());
            if (weedperiodService.save(weedperiod) == null) {
                return null;
            }
            smokesession.setWeedperiod(weedperiod);
        }
        return smokesessionRepository.save(smokesession);
    }
}
