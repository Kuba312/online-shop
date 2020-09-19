package pl.me.shop.mapper;

import pl.me.shop.model.dao.Product;
import pl.me.shop.model.dto.ProductDto;

public interface ProductMapper {

    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
}
