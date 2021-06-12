package pl.me.shop.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.me.shop.controller.ProductController;
import pl.me.shop.model.dao.Product;
import pl.me.shop.repository.ProductRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductController productController;

    @Test
    void getProductsTest() {
        //given
        Product product = new Product();
        product.setId(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));

        //when
        Product result = productServiceImpl.getByIdProduct(1L);

        //then
        Assert.assertEquals(new Product(), result);
    }

    @Test
    void saveProductTest() {
        //given
        Product product = new Product();

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(new Product());

        //when
        Product result = productServiceImpl.saveProduct(product);

        //then
        Assert.assertEquals(new Product(), result);

    }

    @Test
    void deleteProductByIdTest() {
        //given
        Product product = new Product();
        product.setId(1L);

        //when
        productServiceImpl.deleteById(1L);

        //then
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);

    }

}
