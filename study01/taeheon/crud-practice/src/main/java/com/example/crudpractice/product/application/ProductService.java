package com.example.crudpractice.product.application;

import com.example.crudpractice.product.api.dto.request.ProductSaveRequest;
import com.example.crudpractice.product.api.dto.request.ProductUpdateRequest;
import com.example.crudpractice.product.api.dto.response.ProductInfoResponse;
import com.example.crudpractice.product.api.util.ProductConverter;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(ProductSaveRequest requestDto) {
        Product product = ProductConverter.toEntity(requestDto);
        productRepository.save(product);
    }

    public List<ProductInfoResponse> findAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductConverter::toDTO)
                .collect(Collectors.toList());
    }

    public ProductInfoResponse findProductById(Long id) {
        Product product = checkProduct(id);
        return ProductConverter.toDTO(product);
    }

    @Transactional
    public void updateProduct(Long id, ProductUpdateRequest requestDto) {
        Product product = checkProduct(id);
        product.update(requestDto);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = checkProduct(id);
        productRepository.delete(product);
    }

    private Product checkProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 상품이 존재하지 않습니다 id = " + id));
        return product;
    }
}
