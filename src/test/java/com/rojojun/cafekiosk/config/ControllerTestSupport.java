package com.rojojun.cafekiosk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rojojun.cafekiosk.api.controller.order.OrderController;
import com.rojojun.cafekiosk.api.controller.product.ProductController;
import com.rojojun.cafekiosk.api.service.order.OrderService;
import com.rojojun.cafekiosk.api.service.product.ProductService;
import com.rojojun.cafekiosk.domain.order.OrderRepository;
import com.rojojun.cafekiosk.domain.orderProduct.OrderProductRepository;
import com.rojojun.cafekiosk.domain.product.ProductRepository;
import com.rojojun.cafekiosk.domain.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OrderService orderService;

    @MockBean
    private ProductService productService;
}
