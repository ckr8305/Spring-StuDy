package com.example.crudpractice.product.api;

import com.example.crudpractice.product.api.dto.reqeust.ProductReqDto;
import com.example.crudpractice.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<String> saveProduct(@RequestBody ProductReqDto productReqDto) {
        productService.saveProduct(productReqDto);
        return new ResponseEntity<>("상품 저장 !", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<String> getAllProduct() {
        productService.getAllProduct();
        return new ResponseEntity<>("상품 전체 조회 !", HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> getProductByName(@PathVariable("name") String name) {
        productService.getProductByName(name);
        return new ResponseEntity<>("상품 조회 !", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") long id, ProductReqDto productReqDto) {
        productService.updateProduct(id, productReqDto);
        return new ResponseEntity<>("상품 수정 !", HttpStatus.OK);
    }

    @DeleteMapping("id")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("상품 삭제 !", HttpStatus.OK);
    }
}
