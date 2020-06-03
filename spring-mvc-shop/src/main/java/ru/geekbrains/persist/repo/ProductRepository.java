package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);

    Page<Product> findByCostGreaterThan(BigDecimal minCost, Pageable pageable);

    Page<Product> findByCostLessThan(BigDecimal maxCost, Pageable pageable);

    Page<Product> findByCostBetween(BigDecimal minCost, BigDecimal maxCost, Pageable pageable);

}
