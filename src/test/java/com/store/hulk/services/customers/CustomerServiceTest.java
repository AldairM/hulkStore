package com.store.hulk.services.customers;

import com.store.hulk.models.customers.Customer;
import com.store.hulk.models.users.UserHulk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Test
    void save() {

        Customer customer = new Customer(0,"Wandie","Bernot","Harbinson",false);
        customer = service.save(customer);

        assertThat(customer.getId()).isGreaterThan(0);


    }

    @Test
    void findById() {
        Optional<Customer> customer = service.findById(Long.valueOf(1));
        assertThat(customer).isNotNull();
    }

    @Test
    void getAll() {
        Collection<Customer> all = (Collection<Customer>) service.getAll();
        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    void typeHeadSearch() {
        Collection<Customer> all = (Collection<Customer>) service.typeHeadSearch("Wandie");
        assertEquals(all.size(),1);
    }
}