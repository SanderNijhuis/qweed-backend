package com.qweed.backend.service;

import com.qweed.backend.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("smokesessionService")
public class DefaultSmokesessionService implements SmokesessionService {
    @Autowired
    SmokesessionRepository smokesessionRepository;

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
        return smokesessionRepository.save(smokesession);
    }
}
