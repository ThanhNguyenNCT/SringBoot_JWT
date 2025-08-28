package Cybersoft.ExJPA_Security.services;

import Cybersoft.ExJPA_Security.entity.Product;

import java.util.List;

public interface ProductService {
    Product insertProduct(String name, double price);
    Product getById(int id);
    List<Product> getAllProduct();
    Product editProduct(int id, Product product);
    void deleteProduct(int id);
}
