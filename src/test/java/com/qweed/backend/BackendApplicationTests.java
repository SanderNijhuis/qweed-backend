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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

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

    //@InjectMocks
   // private final WeedperiodService weedperiodService = new DefaultWeedperiodService();

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

    /*@BeforeEach
    void setMockOutputFindWeedPeriodByID() {
        Weedperiod weedperiod = createTestWeedperiod();
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findCustomerByUserName(TEST_USER_NAME)).thenReturn(optionalCustomer);
    }*/

    @DisplayName("Test Find")
    @Test
    void testFind() {
        Customer customer = customerService.findByUserName(TEST_USER_NAME);
        String retrievedUserName = customer.getUserName();
        assertEquals("Hello Mockito From Repository", retrievedUserName, TEST_USER_NAME);
    }


    @DisplayName("Test Calculate stats")
    @Test
    void testCalculateStats() {


       // if (customerService.save(customer) == null)

        //Customer retrievedCustomer = customerService.findByUserName(TEST_USER_NAME);
        //String retrievedUserName = customer.getUserName();
        //assertEquals("Hello Mockito From Repository", retrievedUserName, TEST_USER_NAME);
    }

}
