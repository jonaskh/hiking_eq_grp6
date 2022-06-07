package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;

    public List<Product> listAll() {
        return(List<Product>) productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if ( result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Could not be find any products with ID " + id);

    }

    public void delete(Integer id) throws ProductNotFoundException {
        Integer count= productRepository.countById(id);
        if (count == null || count == 0) {
            throw new ProductNotFoundException("Could not find any users with ID " + id);
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void increaseAmount(int productId, int amount) throws ProductNotFoundException {
        Product product = productRepository.findProductById(productId);
        if (product == null) throw new ProductNotFoundException("Product not found");

        int update = product.getProductAmount() + amount;
        product.setProductAmount(update);
        productRepository.save(product);
    }

    @Transactional
    public void decreaseAmount(int productId, int amount) throws ProductNotFoundException {
        Product product = productRepository.findProductById(productId);
        if (product == null) throw new ProductNotFoundException("Product not found");
        if (product.getProductAmount() <= 0) throw new ProductNotFoundException("Product out of stock");

        int update = product.getProductAmount() - amount;
        product.setProductAmount(update);
        productRepository.save(product);
    }
}
