/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.controllers.documents;

import com.store.hulk.models.documents.CommercialDocument;
import com.store.hulk.models.documents.TypeDocument;
import com.store.hulk.models.products.Product;
import com.store.hulk.services.documents.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/document"})
public class DocumentController {

    @Autowired
    private DocumentService service;

    @RequestMapping(value = {"/typeHeadSearch"}, method = RequestMethod.GET)
    public Iterable<TypeDocument> typeHeadSearch(@RequestParam("query") String query) {
        return service.typeHeadSearchTypeDocument(query);
    }

    @RequestMapping(value = {"/save", "/guardar", "/s", "/g"}, method = RequestMethod.POST)
    public CommercialDocument save(@RequestBody CommercialDocument document) {
        return service.save(document);
    }

    @RequestMapping(value = {"/typeHeadSearchPage"}, method = RequestMethod.GET)
    public Page<CommercialDocument> typeHeadSearchPage(@RequestParam("page") int page,
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
}
