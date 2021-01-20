package com.qweed.backend;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.jpa.WeedperiodRepository;
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
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class BackendServiceTests {
    private static final String TEST_USER_NAME = "MY_TEST_USER_NAME";

    @Mock
    private CustomerRepository customerRepository = new TestCustomerRepository();

    @Mock
    private WeedperiodRepository weedperiodRepository= new TestWeedperiodRepository();

    @Mock
    private WeedperiodService weedperiodService = new DefaultWeedperiodService();

    @InjectMocks
    private final CustomerService customerService = new DefaultCustomerService();

    //@InjectMocks
   // private final WeedperiodService weedperiodService = new DefaultWeedperiodService();

    /*private Customer createTestCustomer() {
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


    @DisplayName("Test Find Customer")
    @Test
    void testFind() {
        Customer customer = customerService.findByUserName(TEST_USER_NAME);
        String retrievedUserName = "";
        if (customer != null) {
            retrievedUserName = customer.getUserName();
        }
        assertEquals("Hello Mockito From Repository", retrievedUserName, TEST_USER_NAME);
    }

    @DisplayName("Test CreatCustomer")
    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setCustomer(customer);

        ArrayList<Weedperiod> weedperiods = new ArrayList<>();
        weedperiods.add(weedperiod);
        customer.setWeedperiods(weedperiods);

        String retrievedUserName = "";
        Customer retrievedCustomer = customerService.save(customer);
        if (retrievedCustomer != null){

        }
        assertEquals("Hello Mockito From Repository", customer, retrievedCustomer);
    }

     */



}
