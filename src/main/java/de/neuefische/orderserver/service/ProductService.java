package de.neuefische.orderserver.service;

import de.neuefische.orderserver.db.ProductDb;
import de.neuefische.orderserver.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDb productDb;

    @Autowired
    public ProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    public List<Product> listProducts() {
        return productDb.list();
    }

    public Optional<Product> getProductById(String id) {
        return productDb.getById(id);
    }
}
