package com.qweed.backend.controller;

import com.qweed.backend.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class TokenController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/token", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = customerService.login(username, password);

        if (StringUtils.isEmpty(token)) {
            return new ResponseEntity<>("Incorrect login details.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}