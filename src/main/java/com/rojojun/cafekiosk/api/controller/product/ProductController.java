package com.rojojun.cafekiosk.api.controller.product;

import com.rojojun.cafekiosk.api.controller.product.dto.request.ProductCreateRequest;
import com.rojojun.cafekiosk.api.service.product.ProductService;
import com.rojojun.cafekiosk.api.service.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public void createProduct(ProductCreateRequest productCreateRequest) {
        productService.createProduct(productCreateRequest);
    }

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }
}
