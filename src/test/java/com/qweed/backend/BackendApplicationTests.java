package com.qweed.backend;

import com.qweed.backend.jpa.*;
import com.qweed.backend.service.CustomerService;
import com.qweed.backend.service.DefaultCustomerService;
import com.qweed.backend.service.DefaultWeedperiodService;
import com.qweed.backend.service.WeedperiodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class BackendApplicationTests {
    private static final String TEST_USER_NAME = "MY_TEST_USER_NAME";

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private WeedperiodRepository weedperiodRepository;

    @Mock
    private WeedperiodService weedperiodService = new DefaultWeedperiodService();

    @InjectMocks
    private final CustomerService customerService = new DefaultCustomerService();


    private Customer createTestCustomer() {
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setCustomer(customer);

        ArrayList<Weedperiod> weedperiods = new ArrayList<>();
        weedperiods.add(weedperiod);
        customer.setWeedperiods(weedperiods);

        return customer;
    }
    /*
    private Weedperiod createTestWeedperiod() {
        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setId((long) 1);
        weedperiod.setCustomerName(TEST_USER_NAME);
        weedperiod.setStartDate(new Date());
      //  weedperiod.setPassword("pass");
       // weedperiod.setMotivation("Very, very low.");

        Customer customer = createTestCustomer();
        weedperiod.setCustomer(customer);
        //customer.setWeedperiods(weedperiods);
        Smokesession smokesession = new Smokesession();
        smokesession.setWeedperiod(weedperiod);

        ArrayList<Smokesession> smokesessions = new ArrayList<>();
        smokesessions.add(smokesession);
        weedperiod.setSmokesessions(smokesessions);

        return weedperiod;
    }
    private List<Customer>Customers;*/

    @BeforeEach
    void setMockOutputFindCustomerByUsername() {
        Customer customer = createTestCustomer();
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findCustomerByUserName(TEST_USER_NAME)).thenReturn(optionalCustomer);
    }

    @BeforeEach
    void setMockOutputFindWeedperiodByCustomerAndIsInitial() {
        Customer customer = customerRepository.findCustomerByUserName(TEST_USER_NAME).get();
        Weedperiod weedperiod = customer.getWeedperiods().get(0);

        when(weedperiodRepository.findWeedperiodByCustomerAndIsInitial(customer, true)).thenReturn(Optional.of(weedperiod));
    }

    @BeforeEach
    void setMockOutputSave() {
        Customer customer = createTestCustomer();
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    }
    @BeforeEach
    void setMockOutputLogin() {
        Customer customer = createTestCustomer();
        String token = "f5311068-df36-4d33-abd7-d530153c63dc";
       // when(customerRepository.login(customer.getUserName(),customer.getPassword())).thenReturn(token);
    }

    /*@BeforeEach
    void setMockOutputFindWeedPeriodByID() {
        Weedperiod weedperiod = createTestWeedperiod();
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findCustomerByUserName(TEST_USER_NAME)).thenReturn(optionalCustomer);
    }*/

    @DisplayName("Test Find")
    //@Test
    void testFind() {
        Customer customer = customerService.findByUserName(TEST_USER_NAME);
        assertEquals("Hello Mockito From Repository", customer.getUserName(), TEST_USER_NAME);
    }
    @DisplayName("Test Create")
    //@Test
    void testSave() {
        Customer customer = customerService.findByUserName(TEST_USER_NAME);
        customer = customerService.save(customer);
        assertEquals("Hello Mockito From Repository", customer.getUserName(), TEST_USER_NAME);
    }
    @DisplayName("Test Login")
    void testLogin() {

        String token = customerService.login(TEST_USER_NAME, "pass");
        assertNotNull("Hello Mockito From Repository",token);
    }


}
