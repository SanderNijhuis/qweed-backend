package com.qweed.backend.controller;
import com.qweed.backend.jpa.Smokesession;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.service.CustomerService;
import com.qweed.backend.service.SmokesessionService;
import com.qweed.backend.service.WeedperiodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/smokesessions")
public class SmokesessionController {
    @Autowired
    private SmokesessionService smokesessionService;

    @Autowired
    private WeedperiodService weedperiodService;

    @PostMapping(value = "/smokesession", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Smokesession> createSmokesession(@RequestBody Smokesession smokesession) {
        smokesession.setWeedperiod(weedperiodService.findByID(smokesession.getWeedperiodID()));
        if (smokesessionService.save(smokesession) == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(smokesession, HttpStatus.OK);
    }

    @GetMapping("/weedperiod/{id}")
    public @ResponseBody
    ResponseEntity<List> getAllSmokesessions(@PathVariable Long id) {
        return new ResponseEntity<>(weedperiodService.findByID(id).getSmokesessions(), HttpStatus.OK);
    }

    @GetMapping(value = "/smokesession/{id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Smokesession> getSmokesession(@PathVariable Long id) {
        return new ResponseEntity<>(smokesessionService.findByID(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/smokesession/{id}", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> deleteWeedPeriod(@PathVariable Long id) {

        smokesessionService.deleteByID(id);
        return new ResponseEntity<>("Executed. Status unknown.", HttpStatus.OK);
    }
}