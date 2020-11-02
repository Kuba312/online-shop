package pl.me.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.me.shop.mapper.ProductMapper;
import pl.me.shop.model.dto.ProductDto;
import pl.me.shop.repository.ProductRepository;
import pl.me.shop.service.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productMapper.productToProductDto(productService.getByIdProduct(id));
    }
    @GetMapping
    public Page<ProductDto> getPageProduct(@RequestParam Integer page, @RequestParam Integer size){
        return productService.getPageProduct(PageRequest.of(page, size))

                .map(productMapper::productToProductDto);
    }
    //Post nie zawsze musi zwrócić ta sama odpowiedź dla dwóch tych samych requestów
    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        return productMapper.productToProductDto(productService.saveProduct(productMapper.productDtoToProduct(productDto)));
    }
    @DeleteMapping
    public void deleteProductById(@RequestParam Long id){
        productService.deleteById(id);
    }
    //Put zawsze zwróci ta samą odp dla dwóch requestów i więcej (Podmienia wszystkie dane z rekordu w bazie danych)
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productMapper.productToProductDto(productService.saveProduct(productMapper.productDtoToProduct(productDto)));
    }

}
