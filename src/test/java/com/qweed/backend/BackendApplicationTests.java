package com.qweed.backend;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;
import com.qweed.backend.service.CustomerService;
import com.qweed.backend.service.DefaultCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class BackendApplicationTests {
    private static String TEST_USER_NAME = "MY_TEST_USER_NAME";

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private final CustomerService customerService = new DefaultCustomerService();

    @BeforeEach
    void setMockOutput() {
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findCustomerByUserName("MY_TEST_USER")).thenReturn(optionalCustomer);
    }

    @DisplayName("Test Find")
    @Test
    void testFind() {
        Customer customer = customerService.findByUserName(TEST_USER_NAME);
        String retrievedUserName = customer.getUserName();
        assertEquals("Hello Mockito From Repository", retrievedUserName, TEST_USER_NAME);
    }
}
