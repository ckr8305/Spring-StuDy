package com.example.crudpractice.product.api;

import com.example.crudpractice.global.template.RspTemplate;
import com.example.crudpractice.product.application.ProductService;
import com.example.crudpractice.product.dto.request.ProductSaveRequest;
import com.example.crudpractice.product.dto.request.ProductUpdateRequest;
import com.example.crudpractice.product.dto.response.ProductInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public RspTemplate<Void> saveProduct(@RequestBody @Valid ProductSaveRequest requestDto) {
        productService.saveProduct(requestDto);
        return new RspTemplate<>(HttpStatus.CREATED, "상품 저장");
    }

    @GetMapping()
    public RspTemplate<List<ProductInfoResponse>> findAllProduct() {
        List<ProductInfoResponse> products = productService.findAllProduct();
        return new RspTemplate<>(HttpStatus.OK, "전체 상품 조회", products);
    }

    @GetMapping("/{id}")
    public RspTemplate<ProductInfoResponse> findProductById(@PathVariable("id") Long id) {
        ProductInfoResponse product = productService.findProductById(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 조회", product);
    }

    @PatchMapping("/{id}")
    public RspTemplate<ProductInfoResponse> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductUpdateRequest requestDto) {
        productService.updateProduct(id, requestDto);
        ProductInfoResponse product = productService.findProductById(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 수정", product);
    }

    @DeleteMapping("/{id}")
    public RspTemplate<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 삭제");
    }
}
