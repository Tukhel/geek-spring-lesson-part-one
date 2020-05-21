package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

@RequestMapping("/product")
@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String productList (Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("new")
    public String addProduct (Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        productRepository.add(product);
        return "redirect:/product";
    }
}
