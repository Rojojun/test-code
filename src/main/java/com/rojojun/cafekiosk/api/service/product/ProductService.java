package com.rojojun.cafekiosk.api.service.product;

import com.rojojun.cafekiosk.api.controller.product.dto.request.ProductCreateRequest;
import com.rojojun.cafekiosk.api.service.product.response.ProductResponse;
import com.rojojun.cafekiosk.domain.product.Product;
import com.rojojun.cafekiosk.domain.product.ProductRepository;
import com.rojojun.cafekiosk.domain.product.ProductSellingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse> getSellingProducts() {
        List<Product> productList = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return productList.stream()
                .map(ProductResponse::of)
                .toList();
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        String latestProductNumber = createNextProductNumber();

        Product product = productCreateRequest.toEntity(latestProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumber = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumber);
    }
}
