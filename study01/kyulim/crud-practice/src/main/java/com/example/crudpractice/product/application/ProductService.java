package com.example.crudpractice.product.application;

import com.example.crudpractice.global.util.Check;
import com.example.crudpractice.product.api.dto.reqeust.ProductCreateReqDto;
import com.example.crudpractice.product.api.dto.reqeust.ProductUpdateReqDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductInfoResDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductListResDto;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Check check;

    @Transactional
    public void saveProduct(@RequestBody ProductCreateReqDto productReqDto) {
        productRepository.save(productReqDto.toEntity(productReqDto));
    }

    public ProductListResDto getAllProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductInfoResDto> productListResDtos = products.stream()
                .map(ProductInfoResDto::from)
                .toList();
        return ProductListResDto.from(productListResDtos);
    }

    public ProductInfoResDto getProductById(long id) {
        return ProductInfoResDto.from(check.checkProduct(id));
    }

    public ProductInfoResDto getProductByName(String name) {
        return ProductInfoResDto.from(check.checkProductName(name));
    }

    @Transactional
    public void updateProduct(long id, ProductUpdateReqDto productUpdateReqDto) {
        check.checkProduct(id).update(productUpdateReqDto);
    }

    @Transactional
    public void deleteProduct(long id) {
        productRepository.delete(check.checkProduct(id));
    }
}
