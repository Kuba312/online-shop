package pl.me.shop.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.me.shop.model.dao.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product getByIdProduct(Long id);

    void deleteById(Long id);

    Page<Product> getPageProduct(Pageable pageable);


}
