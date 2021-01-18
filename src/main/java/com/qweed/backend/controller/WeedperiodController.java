package com.qweed.backend.controller;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.service.CustomerService;
import com.qweed.backend.service.WeedperiodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping(path = "/api/v1/users")
public class WeedperiodController {
    @Autowired
    private WeedperiodService weedperiodService;

    @PostMapping(value = "/user", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Weedperiod> createWeedPeriod(@RequestBody Weedperiod weedperiod) {
        if (weedperiodService.save(weedperiod) == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(weedperiod, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/users/user/{username}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Weedperiod> getWeedperiod(@PathVariable Long id) {
        return new ResponseEntity<>(weedperiodService.findByID(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/v1/users/user/{username}", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        weedperiodService.deleteByID(id);
        return new ResponseEntity<>("Executed. Status unknown.", HttpStatus.OK);
    }
}