package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Product> ageGreaterThanOrEqual(BigDecimal cost) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> ageLessThanOrEqual(BigDecimal cost) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> findByTitle(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }
}
