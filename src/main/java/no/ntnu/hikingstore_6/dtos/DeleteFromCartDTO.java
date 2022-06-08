package no.ntnu.hikingstore_6.dtos;

import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import no.ntnu.hikingstore_6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DeleteFromCartDTO {
    private int cartitemID;

    @Autowired
    ProductService productService;

    public DeleteFromCartDTO (int cartitemID) {
        this.cartitemID = cartitemID;
    }

    public int getCartitemID() {
        return this.cartitemID;
    }

    public void setCartitemID(int cartitemID) {
        this.cartitemID = cartitemID;
    }

    private Product findProduct(int productID) {
        Product product = new Product();
        try {
            ;
            product = productService.get(productID);

        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        } return product;

    }
}
