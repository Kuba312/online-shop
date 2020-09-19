package pl.me.shop.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.me.shop.model.dao.Product;

public interface ProductService {


    //Zawsze wywołuje metodę, na której jest oznaczona czyli zawsze będzie miała jakiś typ zwracany(product)

    Product saveProduct(Product product);

    //Najpierw sprawdza w cache czy ma obiket pod danym kluczem, jeśli tak, to nie wywołuje metody i zwraca odpowiedź

    Product getByIdProduct(Long id);

    //usuwa obiekt z cache z produktu jesli istnieje

    void deleteById(Long id);

    Page<Product> getPageProduct(Pageable pageable);


}
