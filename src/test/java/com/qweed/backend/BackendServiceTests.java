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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
@Configuration
@TestPropertySource("classpath:application.production.properties")
@EnableTransactionManagement
class BackendServiceTests {
    private static final String TEST_USER_NAME = "MY_TEST_USER_NAME";


    private final CustomerRepository customerRepository = new TestCustomerRepository();


    private final WeedperiodRepository weedperiodRepository= new TestWeedperiodRepository();


    private final WeedperiodService weedperiodService = new DefaultWeedperiodService(weedperiodRepository);


    private final CustomerService customerService = new DefaultCustomerService(customerRepository,weedperiodService);

    @Test
    void createCustomerTest(){
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setCustomer(customer);

        ArrayList<Weedperiod> weedperiods = new ArrayList<>();
        weedperiods.add(weedperiod);
        customer.setWeedperiods(weedperiods);

        Customer retrieved = customerService.save(customer);

        assertEquals("Hello Mockito From Repository", retrieved.getUserName(), customer.getUserName());
    }
    @Test
    void loginCustomerTest(){
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setCustomer(customer);

        ArrayList<Weedperiod> weedperiods = new ArrayList<>();
        weedperiods.add(weedperiod);
        customer.setWeedperiods(weedperiods);

        Customer retrieved = customerService.save(customer);
        String token = customerService.login(customer.getUserName(),customer.getPassword());

        assertNotNull("Hello Mockito From Repository", token);
        assertNotEquals("Hello Mockito From Repository","",token);
    }
    @Test
    void getCustomerTest(){
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setCustomer(customer);
        weedperiod.setCostPerGram(12d);
        weedperiod.setAverageGramPerJoint(1.2d);
        weedperiod.setAverageJointsSmokedPerWeek(22L);
        weedperiod.setAverageDurationPerWeek(202L);
        weedperiod.setIsInitial(true);

        ArrayList<Weedperiod> weedperiods = new ArrayList<>();
        weedperiods.add(weedperiod);

        customer.setWeedperiods(weedperiods);

        Customer retrieved = customerService.save(customer);
        Customer retrieved_findByUserName = customerService.findByUserName(retrieved.getUserName());
        assertEquals("Hello Mockito From Repository", retrieved_findByUserName.getUserName(), customer.getUserName());
    }
    @Test
    void deleteCustomerTest(){
        Customer customer = new Customer();
        customer.setUserName(TEST_USER_NAME);
        customer.setPassword("pass");
        customer.setMotivation("Very, very low.");

        Weedperiod weedperiod = new Weedperiod();
        weedperiod.setCustomer(customer);
        weedperiod.setCostPerGram(12d);
        weedperiod.setAverageGramPerJoint(1.2d);
        weedperiod.setAverageJointsSmokedPerWeek(22L);
        weedperiod.setAverageDurationPerWeek(202L);
        weedperiod.setIsInitial(true);

        ArrayList<Weedperiod> weedperiods = new ArrayList<>();
        weedperiods.add(weedperiod);

        customer.setWeedperiods(weedperiods);

        Customer retrieved = customerService.save(customer);
        customerService.deleteByUserName(retrieved.getUserName());
        Customer retrieved_findByUserName = customerService.findByUserName(retrieved.getUserName());
        assertNull("Hello Mockito From Repository", retrieved_findByUserName);
    }

    @DisplayName("Test Create Weedperiod")
    @Test
    void testCreateWeedperiod() {
        Weedperiod weedPeriod = new Weedperiod();
        weedPeriod.setName("Weed Period");
        weedPeriod.setCustomerName("Jantje");
        weedPeriod.setCustomer(new Customer());

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.set(GregorianCalendar.YEAR, 2014);
        startCalendar.set(GregorianCalendar.MONTH, 7);
        startCalendar.set(GregorianCalendar.DATE, 23);
        Date startDate = startCalendar.getTime();

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.set(GregorianCalendar.YEAR, 2015);
        endCalendar.set(GregorianCalendar.MONTH, 7);
        endCalendar.set(GregorianCalendar.DATE, 23);
        Date endDate = endCalendar.getTime();


        weedPeriod.setStartDate(startDate);
        weedPeriod.setEndDate(endDate);
        weedPeriod.setIsInitial(true);
        weedPeriod.setAverageDurationPerWeek(10L);
        weedPeriod.setAverageGramPerJoint(1d);
        weedPeriod.setAverageJointsSmokedPerWeek(20L);
        weedPeriod.setCostPerGram(3d);

        Weedperiod retrieved = weedperiodService.save(weedPeriod);
        assertEquals("Stat X calculated correctly", weedPeriod.getName(), retrieved.getName());
    }

    @DisplayName("Test Calculate Stats")
    @Test
    void testCalculateStatsWeedperiodCostPerJoint() {
        Weedperiod weedPeriod = new Weedperiod();
        weedPeriod.setName("Weed Period");
        weedPeriod.setCustomerName("Jantje");
        weedPeriod.setCustomer(new Customer());

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.set(GregorianCalendar.YEAR, 2014);
        startCalendar.set(GregorianCalendar.MONTH, 7);
        startCalendar.set(GregorianCalendar.DATE, 23);
        Date startDate = startCalendar.getTime();

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.set(GregorianCalendar.YEAR, 2015);
        endCalendar.set(GregorianCalendar.MONTH, 7);
        endCalendar.set(GregorianCalendar.DATE, 23);
        Date endDate = endCalendar.getTime();

        weedPeriod.setStartDate(startDate);
        weedPeriod.setEndDate(endDate);
        weedPeriod.setIsInitial(true);
        weedPeriod.setAverageDurationPerWeek(10L);
        weedPeriod.setAverageGramPerJoint(1d);
        weedPeriod.setAverageJointsSmokedPerWeek(20L);
        weedPeriod.setCostPerGram(3d);

        weedPeriod = weedperiodService.calculateStats(weedPeriod);
        assertEquals("Stat X calculated correctly", weedPeriod.getCostPerJoint(), 3.0);
    }

    @DisplayName("Test Calculate Stats")
    @Test
    void testCalculateStatsWeedperiodCostPerWeek() {
        Weedperiod weedPeriod = new Weedperiod();
        weedPeriod.setName("Weed Period");
        weedPeriod.setCustomerName("Jantje");
        weedPeriod.setCustomer(new Customer());

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(new Date(2019, Calendar.FEBRUARY, 1));
        Date startDate = startCalendar.getTime();

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(new Date(2020, Calendar.FEBRUARY, 1));
        Date endDate = startCalendar.getTime();

        weedPeriod.setStartDate(startDate);
        weedPeriod.setEndDate(endDate);
        weedPeriod.setIsInitial(true);
        weedPeriod.setAverageDurationPerWeek(10L);
        weedPeriod.setAverageGramPerJoint(1d);
        weedPeriod.setAverageJointsSmokedPerWeek(20L);
        weedPeriod.setCostPerGram(3d);

        weedPeriod = weedperiodService.calculateStats(weedPeriod);
        assertEquals("Stat X calculated correctly", weedPeriod.getAverageCostPerWeek(), 60.0);
    }
}
