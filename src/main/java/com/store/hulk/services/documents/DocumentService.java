/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.services.documents;

import com.store.hulk.models.customers.Customer;
import com.store.hulk.models.documents.CommercialDocument;
import com.store.hulk.models.documents.CommercialDocumentDetail;
import com.store.hulk.models.documents.TypeDocument;
import com.store.hulk.models.products.Product;
import com.store.hulk.repositories.documents.ICommercialDocumentRepository;
import com.store.hulk.repositories.documents.ITypeDocumentRepository;
import com.store.hulk.services.products.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service @Slf4j
public class DocumentService {
    @Autowired
    private ICommercialDocumentRepository documentRepository;

    @Autowired
    private ITypeDocumentRepository typeDocumentRepository;

    @Autowired
    private ProductService productService;

    @Async("asyncExecutor")
    public CompletableFuture<Iterable<TypeDocument>> typeHeadSearchTypeDocument(String query) {
        log.info("type head search by query: {}",query);
        if (query.equals("")) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
        query = "%" + query + "%";
        return CompletableFuture.completedFuture(typeDocumentRepository.typeHeadSearch(query));
    }

    public TypeDocument saveTypeDocument(TypeDocument typeDocument){
        log.info("Save type document: {}",typeDocument.getName());
        try {
            return typeDocumentRepository.save(typeDocument);
        }catch (DataAccessException e){
            log.error("Error type document:{}",e.getMessage());
        }
        return new TypeDocument();
    }

    public CommercialDocument save(CommercialDocument document){
        log.info("Save document: {}",document.getTypeDocument().getName());
        try {
            document.setConsecutive(documentRepository.count(document.getTypeDocument().getId())+1);
            for (CommercialDocumentDetail detail : document.getDetails()) {
                if(document.getTypeDocument().isInventoryOutput()){
                    productService.updateStockItemQuantityMinus((long) detail.getQuantity(),detail.getProduct().getId());
                }else{
                    productService.updateStockItemQuantity((long) detail.getQuantity(),detail.getProduct().getId());
                }
            }
            return documentRepository.save(document);
        }catch (DataAccessException e){
            log.error("Error document:{}",e.getMessage());
        }
        return new CommercialDocument();
    }

    public long countTypeDocument(Long id){
        return typeDocumentRepository.count(id);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Page<CommercialDocument>> typeHeadSearchPage(String query, Pageable pageable) {
        log.info("type head search by query: {}",query);
        query = "%" + query + "%";
        return CompletableFuture.completedFuture(documentRepository.typeHeadSearchPage(query, pageable));
    }
}
