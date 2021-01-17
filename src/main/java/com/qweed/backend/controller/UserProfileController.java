package com.qweed.backend.controller;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://127.0.0.1:8081")
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserProfileController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody Iterable<Customer> getUserDetail() {
        return customerService.findAll();
    }

    @PostMapping(value = "/user", produces = "application/json")
    public @ResponseBody Customer createUser(@RequestParam String username, @RequestParam String password) {
        final Customer customer = new Customer();
        customer.setUserName(username);
        customer.setPassword(password);
        return customerService.save(customer);
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public @ResponseBody Customer getUserDetail(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @DeleteMapping(value = "/user/{id}", produces = "text/plain")
    public @ResponseBody String deleteUser(@PathVariable Long id) {
        customerService.deleteById(id);
        return "Executed. Status unknown.";
    }
}