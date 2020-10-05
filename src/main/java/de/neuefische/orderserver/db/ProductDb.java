package de.neuefische.orderserver.db;

import de.neuefische.orderserver.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDb {

    private final ArrayList<Product> products = new ArrayList<>(List.of(
            new Product("erbsen", "Erbsen"),
            new Product("tomate", "Tomate")));


    public List<Product> list(){
        return products;
    }

    public Optional<Product> getById(String id) {
        for (Product product : products) {
            if(product.getId().equals(id)){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}
