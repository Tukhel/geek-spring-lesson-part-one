package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    public Page<Product> filterByCost(BigDecimal minCost, BigDecimal maxCost, Pageable pageable) {

        if(minCost == null && maxCost != null) {
            return repository.findByCostLessThan(maxCost, pageable);
        }
        if(minCost != null && maxCost == null) {
            return repository.findByCostGreaterThan(minCost, pageable);
        }
        if(minCost != null) {
            return repository.findByCostBetween(minCost, maxCost, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }
}
