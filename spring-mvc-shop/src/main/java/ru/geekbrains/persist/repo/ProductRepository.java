package ru.geekbrains.persist.repo;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.enity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {

    private final AtomicInteger identityGen;

    private final Map<Integer, Product> products;

    public ProductRepository() {
        this.identityGen = new AtomicInteger(0);
        this.products = new ConcurrentHashMap<>();
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public void add(Product products) {
        int id = identityGen.incrementAndGet();
        products.setId(id);
    }

    public Product findById (int id) {
        return products.get(id);
    }
}
