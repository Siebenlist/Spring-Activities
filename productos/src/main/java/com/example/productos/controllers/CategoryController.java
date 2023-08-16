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

import javax.naming.Binding;
import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService){
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/category/new")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @PostMapping("/category/new")
    public String createCategory(@Valid @ModelAttribute("category")Category c, BindingResult result){
        if(result.hasErrors()){
            return "categoryForm";
        }
        categoryService.createCategory(c);
        return "categoryForm";
    }

    @GetMapping("/category/{id}")
    public String showCategories(@PathVariable("id") Long id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        List<Product> allProducts = productService.findAll();
        model.addAttribute("allProducts", allProducts);
        return "viewCategories";
    }

    @PostMapping("/category/{id}")
    public String addProduct(@PathVariable("id") Long id, @RequestParam("productId") Long productId){
        Category category = categoryService.findById(id);
        Product product = productService.findProduct(productId);

        if (!category.getProducts().contains(product)) {
            category.getProducts().add(product);
            categoryService.createCategory(category);
        }
        return "redirect:/category/" + id;
    }
}
