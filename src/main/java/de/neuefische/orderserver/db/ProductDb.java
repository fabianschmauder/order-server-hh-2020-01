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


    public List<Product> search(String q){
        if(q == null || q.isBlank()){
            return products;
        }

        ArrayList<Product> matchingProducts = new ArrayList<>();

        for (Product product : products) {
            if(product.getName().toLowerCase().contains(q.toLowerCase())){
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
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
