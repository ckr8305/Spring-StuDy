package com.example.crudpractice.product.application;

import com.example.crudpractice.product.api.dto.reqeust.ProductCreateReqDto;
import com.example.crudpractice.product.api.dto.reqeust.ProductUpdateReqDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductInfoResDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductListResDto;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(@RequestBody ProductCreateReqDto productReqDto) {
        Product product = productReqDto.toEntity(productReqDto);
        productRepository.save(product);
    }

    public ProductListResDto getAllProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductInfoResDto> productListResDtos = products.stream()
                .map(ProductInfoResDto::from)
                .toList();
        return ProductListResDto.from(productListResDtos);
    }

    public ProductInfoResDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductInfoResDto.from(product);
    }

    public ProductInfoResDto getProductByName(String name) {
        Product product = productRepository.findByName(name);
        return ProductInfoResDto.from(product);
    }

    @Transactional
    public void updateProduct(long id, ProductUpdateReqDto productUpdateReqDto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.update(productUpdateReqDto);
    }

    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
