package de.neuefische.orderserver.controller;

import de.neuefische.orderserver.db.ProductDb;
import de.neuefische.orderserver.model.Product;
import de.neuefische.orderserver.service.ProductService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private final ProductService service = mock(ProductService.class);
    private final ProductController productController = new ProductController(service);

    @Test
    public void getProductByIdShouldReturnMatchingProduct() {
        //GIVEN
        String productId = "some-product-id";

        Optional<Product> returnValue = Optional.of(new Product(
                productId,
                "some Product name"
        ));

        when(service.getProductById(productId)).thenReturn(returnValue);

        //WHEN
        Product product = productController.getProduct(productId);

        //THEN
        assertThat(product, is(new Product(
                productId,
                "some Product name"
        )));

        // sicherstellen das getProductById aufgerufen wurde
        verify(service).getProductById(productId);
    }
}
