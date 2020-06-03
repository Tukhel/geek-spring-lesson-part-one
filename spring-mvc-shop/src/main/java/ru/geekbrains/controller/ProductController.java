package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

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
                               @RequestParam(value = "minCost", required = false) BigDecimal minCost,
                               @RequestParam(value = "maxCost", required = false) BigDecimal maxCost,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        Page<Product> productPage = productService.filterByCost(minCost, maxCost,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("productsPage", productPage);
        model.addAttribute("prevPageNumber", productPage.hasPrevious() ? productPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", productPage.hasNext() ? productPage.nextPageable().getPageNumber() + 1 : -1);
        return "products";
    }

    @GetMapping("new")
    public String addProduct (Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "product";
        }

        double cost = product.getCost().doubleValue();
        if (cost < 0) {
            bindingResult.rejectValue("wrongCoat", "", "неверная цена");
            return "product";
        }

        productService.save(product);
        return "redirect:/product";
    }
}
