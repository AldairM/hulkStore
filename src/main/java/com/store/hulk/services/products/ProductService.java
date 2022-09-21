/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.services.products;

import com.store.hulk.models.customers.Customer;
import com.store.hulk.models.products.Category;
import com.store.hulk.models.products.Product;
import com.store.hulk.repositories.products.ICategoryRepository;
import com.store.hulk.repositories.products.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service @Slf4j
public class ProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private ICategoryRepository categoryRepository;


    public Category saveCategory(Category category){
        log.info("Save category: {}",category.getName());
        try {
            return categoryRepository.save(category);
        }catch (DataAccessException e){
            log.error("Error category:{}",e.getMessage());
        }
        return  new Category();
    }

    public Product save(Product product){
        log.info("Save product: {}",product.getName());
        try {
            return repository.save(product);
        }catch (DataAccessException e){
            log.error("Error product:{}",e.getMessage());
        }
        return  new Product();
    }

    public Optional<Product> findById(Long id){
        log.info("Find product by id: {}",id);
        return  repository.findById(id);
    }

    public Iterable<Product> getAll(){
        log.info("Get all products");
        return repository.findAll();
    }

    public Iterable<Product> typeHeadSearch(String query) {
        log.info("type head search by query: {}",query);
        if (query.equals("")) {
            return new ArrayList<>();
        }
        query = "%" + query + "%";
        return repository.typeHeadSearch(query);
    }

    public Page<Product> typeHeadSearchPage(String query, Pageable pageable) {
        log.info("type head search by query: {}",query);
        query = "%" + query + "%";
        return repository.typeHeadSearchPage(query, pageable);
    }

    public void updateStockItemQuantity(Long quantity,Long id){
          repository.updateStockItemQuantity(quantity,id);
    }

    public void updateStockItemQuantityMinus(Long quantity,Long id){
          repository.updateStockItemQuantityMinus(quantity,id);
    }
}
