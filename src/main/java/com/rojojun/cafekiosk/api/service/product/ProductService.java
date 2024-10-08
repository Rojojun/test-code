package com.rojojun.cafekiosk.api.service.product;

import com.rojojun.cafekiosk.api.service.product.response.ProductResponse;
import com.rojojun.cafekiosk.domain.product.Product;
import com.rojojun.cafekiosk.domain.product.ProductRepository;
import com.rojojun.cafekiosk.domain.product.ProductSellingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> productList = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return productList.stream()
                .map(ProductResponse::of)
                .toList();
    }
}
