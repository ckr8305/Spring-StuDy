package com.example.crudpractice.product.api;

import com.example.crudpractice.global.RspTemplate;
import com.example.crudpractice.product.api.dto.reqeust.ProductCreateReqDto;
import com.example.crudpractice.product.api.dto.reqeust.ProductUpdateReqDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductInfoResDto;
import com.example.crudpractice.product.api.dto.respoonse.ProductListResDto;
import com.example.crudpractice.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        ProductListResDto products = productService.getAllProduct();
        return new RspTemplate<>(HttpStatus.OK, "상품 전체 조회 !", products);
    }

    @GetMapping("/name/{name}")
    public RspTemplate<ProductInfoResDto> getProductByName(@PathVariable("name") String name) {
        ProductInfoResDto product = productService.getProductByName(name);
        return new RspTemplate<>(HttpStatus.OK, "상품 name으로 조회 !", product);
    }

    @GetMapping("/id/{id}")
    public RspTemplate<ProductInfoResDto> getProductById(@PathVariable("id") long id) {
        ProductInfoResDto product = productService.getProductById(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 id로 조회 !", product);
    }

    @PatchMapping("/{id}")
    public RspTemplate<ProductInfoResDto> updateProduct(@PathVariable("id") long id, @RequestBody ProductUpdateReqDto productUpdateReqDto) {
        productService.updateProduct(id, productUpdateReqDto);
        ProductInfoResDto product = productService.getProductById(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 수정 !", product);
    }

    @DeleteMapping("id")
    public RspTemplate<String> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return new RspTemplate<>(HttpStatus.OK, "상품 삭제 !");
    }
}
