package com.qweed.backend.controller;

import com.qweed.backend.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TokenController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/token", produces = "text/plain")
    public @ResponseBody
    String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = customerService.login(username, password);
        if (StringUtils.isEmpty(token)) {
            return "no token found";
        }
        return token;
    }
}