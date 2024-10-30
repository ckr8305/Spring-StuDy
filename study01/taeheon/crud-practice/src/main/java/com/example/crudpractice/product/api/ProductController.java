package com.example.crudpractice.product.api;

import com.example.crudpractice.global.template.RspTemplate;
import com.example.crudpractice.product.application.ProductService;
import com.example.crudpractice.product.api.dto.request.ProductSaveRequest;
import com.example.crudpractice.product.api.dto.request.ProductUpdateRequest;
import com.example.crudpractice.product.api.dto.response.ProductInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
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

    @GetMapping("/{productId}")
    public RspTemplate<ProductInfoResponse> findProductById(@PathVariable("productId") Long productId) {
        ProductInfoResponse product = productService.findProductById(productId);
        return new RspTemplate<>(HttpStatus.OK, "상품 조회", product);
    }

    @PatchMapping("/{productId}")
    public RspTemplate<ProductInfoResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody @Valid ProductUpdateRequest requestDto) {
        productService.updateProduct(productId, requestDto);
        ProductInfoResponse product = productService.findProductById(productId);
        return new RspTemplate<>(HttpStatus.OK, "상품 수정", product);
    }

    @DeleteMapping("/{productId}")
    public RspTemplate<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return new RspTemplate<>(HttpStatus.OK, "상품 삭제");
    }
}
