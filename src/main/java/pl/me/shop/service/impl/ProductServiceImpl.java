package pl.me.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.me.shop.model.dao.Product;
import pl.me.shop.repository.ProductRepository;
import pl.me.shop.service.ProductService;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    //Wstawia do cache wartość zwróconą z funkcji o podanym key i za każdym razem wywołuje funkcje
    @CachePut(cacheNames = "product", key = "#result.id")
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    //Wstawia do cache wartość zwróconą z funkcji jeśli klucza nie było w cachu w przeciwnym wypadku pobiera wartość po kluczu z cache
   @Cacheable(cacheNames = "product", key = "#id")
    @Override
    public Product getByIdProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with " + id + "does not exist"));
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public Page<Product> getPageProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
