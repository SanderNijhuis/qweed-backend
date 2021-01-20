package com.qweed.backend;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    CustomerService customerService;

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testGet() {
        String customerName = "12345_983";

        Customer customer = new Customer();
        customer.setUserName(customerName);
        customer.setPassword("pass");
        customer.setMotivation("low");
        customerService.save(customer);

        Customer retrieved = customerService.findByUserName(customerName);
        assertNotNull("Retrieved customer was null", retrieved);

        String retrievedName = retrieved.getUserName();
        assertEquals("Retrieved customer name was not found in database", customerName, retrievedName);
    }
}
