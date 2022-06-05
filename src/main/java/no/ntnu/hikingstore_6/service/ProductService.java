package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Could not find any products with ID " + id);
        } else {
            productRepository.deleteById(id);
        }
    }
}
