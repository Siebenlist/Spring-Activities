package com.example.productos.controllers;

import com.example.productos.models.Category;
import com.example.productos.models.Product;
import com.example.productos.services.CategoryService;
import com.example.productos.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products/new")
    public String productForm(Model model){
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping("/products/new")
    public String newProduct(@Valid @ModelAttribute("product")Product p, BindingResult result){
        if(result.hasErrors()){
            return "productForm";
        }
        productService.createProduct(p);
        return "productForm";
    }

    @GetMapping("/products/{id}")
    public String showProducts(@PathVariable("id")Long id, Model model){
        Product product = productService.findProduct(id);
        model.addAttribute("product", product);
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("allCategories", allCategories);
        return "viewProducts";
    }

    @PostMapping("/products/{id}")
    public String addCategory(@PathVariable("id")Long id,
                              @RequestParam("categoryId")Long categoryId){
        Product product = productService.findProduct(id);
        Category category = categoryService.findById(categoryId);
        if (!product.getCategories().contains(category)){
            product.getCategories().add(category);
            productService.createProduct(product);
        }

        return "redirect:/products/" + id;
    }
}