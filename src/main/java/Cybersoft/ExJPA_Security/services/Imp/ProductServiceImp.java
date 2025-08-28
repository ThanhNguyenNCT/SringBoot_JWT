package Cybersoft.ExJPA_Security.services.Imp;

import Cybersoft.ExJPA_Security.entity.Product;
import Cybersoft.ExJPA_Security.exception.DataNotFoundException;
import Cybersoft.ExJPA_Security.exception.DuplicateException;
import Cybersoft.ExJPA_Security.exception.InvalidException;
import Cybersoft.ExJPA_Security.repo.ProductRepository;
import Cybersoft.ExJPA_Security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product insertProduct(String name, double price) {
       if(name == null || name.isEmpty()) {
           throw new DataNotFoundException("Product name is required");
       }
         if(price <= 0) {
              throw new InvalidException("Product price must be greater than 0");
         }
       if(productRepository.existsByName(name)) {
           throw new DuplicateException("Product name is already exists");
       }
       Product product = new Product();
       product.setName(name);
       product.setPrice(price);
       return productRepository.save(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product editProduct(int id, Product product) {
        return productRepository.findById(id).map(item -> {
            if(product.getName() != null && !product.getName().isEmpty()) {
                item.setName(product.getName());
            }
            if (product.getPrice() != null) {
                item.setPrice(product.getPrice());
            }

            return productRepository.save(item);
        }).orElseThrow(() -> new DataNotFoundException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(int id) {
        if(!productRepository.existsById(id)) {
            throw new DataNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
