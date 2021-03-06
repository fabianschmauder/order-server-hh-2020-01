package de.neuefische.orderserver.controller;

import de.neuefische.orderserver.model.Product;
import de.neuefische.orderserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String q){
        return service.searchProducts(q);
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable String id){
        Optional<Product> product = service.getProductById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product with id "+ id+" not found");
    }

    @PutMapping
    public Product addProduct(@RequestBody Product product){
        return service.addProduct(product);
    }
}
