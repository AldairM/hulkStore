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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service @Slf4j
public class CustomerService {

    @Autowired
    private ICustomerRepository repository;


    @Async("asyncExecutor")
    public CompletableFuture< Customer > save(Customer customer){
        log.info("Save customer: {}",customer.getName());
        try {
            return  CompletableFuture.completedFuture( repository.save(customer));
        }catch (DataAccessException e){
            log.error("Error Customer:{}",e.getMessage());
        }
        return CompletableFuture.completedFuture(new Customer());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Optional<Customer>> findById(Long id){
        log.info("Find customer by id: {}",id);
        return  CompletableFuture.completedFuture(repository.findById(id));
    }

    @Async("asyncExecutor")
    public CompletableFuture<Iterable<Customer>> getAll(){
        log.info("Get all customers");
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Iterable<Customer>> typeHeadSearch(String query) {
        log.info("type head search by query: {}",query);
        if (query.equals("")) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
        query = "%" + query + "%";
        return CompletableFuture.completedFuture(repository.typeHeadSearch(query));
    }

    @Async("asyncExecutor")
    public CompletableFuture<Page<Customer>> typeHeadSearchPage(String query, Pageable pageable) {
        log.info("type head search by query: {}",query);
        query = "%" + query + "%";
        return CompletableFuture.completedFuture(repository.typeHeadSearchPage(query, pageable));
    }


}
