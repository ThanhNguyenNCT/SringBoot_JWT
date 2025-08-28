package Cybersoft.ExJPA_Security.controller;

import Cybersoft.ExJPA_Security.entity.Product;
import Cybersoft.ExJPA_Security.payload.respone.BaseRespone;
import Cybersoft.ExJPA_Security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertProduct(@RequestParam String name,
                                           @RequestParam double price) {
        Product product = productService.insertProduct(name, price);
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Insert product successfully");
        baseRespone.setData(product);
        return ResponseEntity.ok(baseRespone);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        List<Product> listProduct = productService.getAllProduct();

        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("All products");
        baseRespone.setData(listProduct);
        return ResponseEntity.ok(baseRespone);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getById(id);

        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("");
        baseRespone.setData(product);
        return ResponseEntity.ok(baseRespone);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) Double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        Product updatedProduct = productService.editProduct(id, product);

        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Update product successfully");
        baseRespone.setData(updatedProduct);
        return ResponseEntity.ok(baseRespone);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Delete product successfully");
        baseRespone.setData(null);
        return ResponseEntity.ok(baseRespone);
    }


}
