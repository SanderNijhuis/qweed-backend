package com.qweed.backend.controller;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.service.CustomerService;
import com.qweed.backend.service.WeedperiodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/weedperiods")
public class WeedperiodController {
    @Autowired
    private WeedperiodService weedperiodService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/weedperiod", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Weedperiod> createWeedPeriod(@RequestBody Weedperiod weedperiod) {
        weedperiod.setCustomer(customerService.findByUserName(weedperiod.customerName));
        if (weedperiodService.save(weedperiod) == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(weedperiod, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public @ResponseBody
    ResponseEntity<List> getAllWeedperiods(@PathVariable String username) {
        return new ResponseEntity<>(customerService.findByUserName(username).getWeedperiods(), HttpStatus.OK);
    }

    @GetMapping(value = "/weedperiod/{id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Weedperiod> getWeedperiod(@PathVariable Long id) {
        return new ResponseEntity<>(weedperiodService.findByID(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/weedperiod/{id}", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> deleteWeedPeriod(@PathVariable Long id) {

        weedperiodService.deleteByID(id);
        return new ResponseEntity<>("Executed. Status unknown.", HttpStatus.OK);
    }
}