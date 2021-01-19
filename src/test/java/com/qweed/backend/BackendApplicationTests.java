package com.qweed.backend;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class BackendApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void whenFindByName_thenReturnCustomer() {
		// given
		Customer alex = new Customer();
		alex.setUserName("alex");
		alex.setPassword("password");
		alex.setMotivation("motivation");
		entityManager.persist(alex);
		entityManager.flush();

		Optional<Customer> found = customerRepository.findCustomerByUserName(alex.getUserName());

		assertThat(found.isPresent());

		Customer foundExists = found.get();

		assertThat(foundExists.getUserName().equals(alex.getUserName()));
	}

}
