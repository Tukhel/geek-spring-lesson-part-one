package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.service.ProductService;

import java.math.BigDecimal;

@RequestMapping("/product")
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productList (Model model,
                               @RequestParam("minCoat") BigDecimal minCoat,
                               @RequestParam("maxCoat") BigDecimal maxCoat) {
        model.addAttribute("products", productService.filterByCoat(minCoat, maxCoat));
        return "products";
    }

    @GetMapping("new")
    public String addProduct (Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/product";
    }
}
