package Cybersoft.ExJPA_Security.controller;

import Cybersoft.ExJPA_Security.entity.Product;
import Cybersoft.ExJPA_Security.payload.respone.BaseResponse;
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
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Insert product successfully");
        baseResponse.setData(product);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        List<Product> listProduct = productService.getAllProduct();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("All products");
        baseResponse.setData(listProduct);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getById(id);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(product);
        return ResponseEntity.ok(baseResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) Double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        Product updatedProduct = productService.editProduct(id, product);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Update product successfully");
        baseResponse.setData(updatedProduct);
        return ResponseEntity.ok(baseResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Delete product successfully");
        baseResponse.setData(null);
        return ResponseEntity.ok(baseResponse);
    }


}
