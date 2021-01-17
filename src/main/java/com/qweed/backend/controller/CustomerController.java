package com.qweed.backend.controller;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/users")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Iterable<Customer>> getUserDetail() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/user", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Customer> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String motivation) {
        final Customer customer = new Customer();
        customer.setUserName(username);
        customer.setPassword(password);
        customer.setMotivation(motivation);
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Customer> getUserDetail(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        customerService.deleteById(id);
        return new ResponseEntity<>("Executed. Status unknown.", HttpStatus.OK);
    }
}