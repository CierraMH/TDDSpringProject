package com.example.TDDSpringProject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController

public class OrderController {
    private OrderServices orderServices;
    @Autowired public OrderController(OrderServices orderServices){
        this.orderServices = orderServices;
    }

    //  Create an order
    @PostMapping("/create")
    public ResponseEntity<OrderEntity> createOrders(@Valid @RequestBody OrderEntity order){
        OrderEntity orderCreate = orderServices.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreate);
    }
//read all orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        List<OrderEntity> orders = orderServices.readOrderList();
        return ResponseEntity.ok(orders);
    }
    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity orderEntity, @PathVariable("id") Long id){
        Optional<OrderEntity> updateOrder = orderServices.updateOrder(orderEntity, id);
        return updateOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderServices.deleteOrderById(id);
        if (deleted){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
