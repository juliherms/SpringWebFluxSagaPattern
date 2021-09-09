package juliherms.com.github.orderms.controller;


import juliherms.com.github.orderms.dto.OrderRequestDTO;
import juliherms.com.github.orderms.model.Order;
import juliherms.com.github.orderms.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return service.createOrder(orderRequestDTO);
    }

    @GetMapping
    public List<Order> getOrders(){
        return service.getAllOrders();
    }
}
