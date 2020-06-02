package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);

    List<Product> findByCoatGreaterThan(BigDecimal minCoat);

    List<Product> findByCoatLessThan(BigDecimal maxCoat);

    List<Product> findByCoatBetween(BigDecimal minCoat, BigDecimal maxCoat);

}
