package de.neuefische.orderserver.db;

import de.neuefische.orderserver.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ProductDbTest {

    @Test
    public void getByIdShouldReturnMatchingProduct(){
        //GIVEN
        ProductDb productDb = new ProductDb();

        //WHEN
        Optional<Product> tomate = productDb.getById("tomate");

        //THEN
        assertThat(tomate.get(),is( new Product("tomate", "Tomate")));
    }

    @Test
    public void getByIdShouldReturnEmptyOptionalWhenProductNotFound(){
        //GIVEN
        ProductDb productDb = new ProductDb();

        //WHEN
        Optional<Product> product = productDb.getById("unknown");

        //THEN
        assertThat(product.isEmpty(),is(true));
    }

}
