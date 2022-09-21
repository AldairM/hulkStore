/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.controllers.customers;

import com.store.hulk.models.customers.Customer;
import com.store.hulk.services.customers.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/customer", "/c"})
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = {"/save", "/guardar", "/s", "/g"}, method = RequestMethod.POST)
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET)
    public Iterable<Customer> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = {"/typeHeadSearch"}, method = RequestMethod.GET)
    public Iterable<Customer> typeHeadSearch(@RequestParam("query") String query) {
        return service.typeHeadSearch(query);
    }

    @RequestMapping(value = {"/typeHeadSearchPage"}, method = RequestMethod.GET)
    public Page<Customer> typeHeadSearchPage(@RequestParam("page") int page,
                                             @RequestParam("size") int size, @RequestParam("query") String query,
                                             @RequestParam("sort") String sort, @RequestParam("order") String order) {
        Sort.Direction d;
        if (order.equalsIgnoreCase("DESC")) {
            d = Sort.Direction.DESC;
        } else {
            d = Sort.Direction.ASC;
        }
        return service.typeHeadSearchPage(query, PageRequest.of(page, size, d, sort));
    }

    @RequestMapping(value = {"/findById"}, method = RequestMethod.GET)
    public Optional<Customer> findById(@RequestParam("id") Long id) {
        return service.findById( id );
    }
}
