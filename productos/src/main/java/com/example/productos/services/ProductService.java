package com.example.productos.services;

import com.example.productos.models.Category;
import com.example.productos.models.Product;
import com.example.productos.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        List<Product> allProducts = productRepository.findAll();
        for(Product product : allProducts){
            List <Category> category;
            category = product.getCategory();
            product.setCategories(category);
        }
        return productRepository.findAll();
    }

    public void createProduct(Product p){
        productRepository.save(p);
    }

    public Product findProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        } else{
            return null;
        }
    }
}
