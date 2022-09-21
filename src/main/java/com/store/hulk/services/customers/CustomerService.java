/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.services.customers;

import com.store.hulk.models.customers.Customer;
import com.store.hulk.repositories.customers.ICustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service @Slf4j
public class CustomerService {

    @Autowired
    private ICustomerRepository repository;


    public Customer save(Customer customer){
        log.info("Save customer: {}",customer.getName());
        try {
            return repository.save(customer);
        }catch (DataAccessException e){
            log.error("Error Customer:{}",e.getMessage());
        }
        return new Customer();
    }

    public Optional<Customer> findById(Long id){
        log.info("Find customer by id: {}",id);
        return  repository.findById(id);
    }

    public Iterable<Customer> getAll(){
        log.info("Get all customers");
        return repository.findAll();
    }

    public Iterable<Customer> typeHeadSearch(String query) {
        log.info("type head search by query: {}",query);
        if (query.equals("")) {
            return new ArrayList<>();
        }
        query = "%" + query + "%";
        return repository.typeHeadSearch(query);
    }

    public Page<Customer> typeHeadSearchPage(String query, Pageable pageable) {
        log.info("type head search by query: {}",query);
        query = "%" + query + "%";
        return repository.typeHeadSearchPage(query, pageable);
    }


}
