package com.example.crudpractice.product.api;

import com.example.crudpractice.global.RspTemplate;
import com.example.crudpractice.product.api.dto.reqeust.ProductCreateReqDto;
import com.example.crudpractice.product.api.dto.reqeust.ProductUpdateReqDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductInfoResDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductListResDto;
import com.example.crudpractice.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public RspTemplate<String> saveProduct(@RequestBody ProductCreateReqDto productReqDto) {
        productService.saveProduct(productReqDto);
        return new RspTemplate<>(HttpStatus.CREATED, "상품 저장 !");
    }

    @GetMapping()
    public RspTemplate<ProductListResDto> getAllProduct() {
        return new RspTemplate<>(HttpStatus.OK, "상품 전체 조회 !", productService.getAllProduct());
    }

    @GetMapping("/name/{name}")
    public RspTemplate<ProductInfoResDto> getProductByName(@PathVariable("name") String name) {
        return new RspTemplate<>(HttpStatus.OK, "상품 name으로 조회 !", productService.getProductByName(name));
    }

    @GetMapping("/id/{id}")
    public RspTemplate<ProductInfoResDto> getProductById(@PathVariable("id") long id) {
        return new RspTemplate<>(HttpStatus.OK, "상품 id로 조회 !", productService.getProductById(id));
    }

    @PatchMapping("/{id}")
    public RspTemplate<ProductInfoResDto> updateProduct(@PathVariable("id") long id, @RequestBody ProductUpdateReqDto productUpdateReqDto) {
        productService.updateProduct(id, productUpdateReqDto);
        return new RspTemplate<>(HttpStatus.OK, "상품 수정 !", productService.getProductById(id));
    }

    @DeleteMapping("id")
    public RspTemplate<String> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 삭제 !");
    }
}
