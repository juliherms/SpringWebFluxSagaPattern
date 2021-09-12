package juliherms.com.github.orderms.controller;


import juliherms.com.github.orderms.dto.OrderRequestDTO;
import juliherms.com.github.orderms.model.Order;
import juliherms.com.github.orderms.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible to provide orders endpoint
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    /**
     * Method responsible to create order
     * @param orderRequestDTO
     * @return
     */
    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return service.createOrder(orderRequestDTO);
    }

    /**
     * Method responsible to list orders
     * @return
     */
    @GetMapping
    public List<Order> getOrders(){
        return service.getAllOrders();
    }
}
