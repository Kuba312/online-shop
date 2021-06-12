package pl.me.shop.mapper.impl;

import org.springframework.stereotype.Component;
import pl.me.shop.mapper.ProductMapper;
import pl.me.shop.model.dao.Product;
import pl.me.shop.model.dto.ProductDto;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .productName(product.getProductName())
                .build();
    }

    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .price(productDto.getPrice())
                .productName(productDto.getProductName())
                .build();
    }
}
