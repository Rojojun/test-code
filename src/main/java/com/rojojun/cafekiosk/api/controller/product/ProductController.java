package com.rojojun.cafekiosk.api.controller.product;

import com.rojojun.cafekiosk.api.ApiResponse;
import com.rojojun.cafekiosk.api.controller.product.dto.request.ProductCreateRequest;
import com.rojojun.cafekiosk.api.service.product.ProductService;
import com.rojojun.cafekiosk.api.service.product.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        return ApiResponse.ok(productService.createProduct(productCreateRequest));
    }

    @GetMapping("/api/v1/products/selling")
    public ApiResponse<List<ProductResponse>> getSellingProducts() {
        return ApiResponse.ok(productService.getSellingProducts());
    }
}
