package pl.me.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.me.shop.model.dao.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
