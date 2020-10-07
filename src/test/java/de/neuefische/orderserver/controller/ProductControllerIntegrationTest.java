package de.neuefische.orderserver.controller;

import de.neuefische.orderserver.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProductByIdShouldReturnProduct() {
        //GIVEN
        String url = "http://localhost:" + port + "/product/tomate";

        //WHEN
        ResponseEntity<Product> productResponse = restTemplate.getForEntity(url, Product.class);

        //THEN
        assertThat(productResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(productResponse.getBody(), is(new Product("tomate", "Tomate")));
    }

}
