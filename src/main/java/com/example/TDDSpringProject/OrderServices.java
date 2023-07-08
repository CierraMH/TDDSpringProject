package com.example.TDDSpringProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServices {
    
    private final OrderRepository orderRepository;
    @Autowired
    public OrderServices(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    //create
    public OrderEntity createOrder(OrderEntity orderEntity){
        return orderRepository.save(orderEntity);
    }
    //readOrders
    public List<OrderEntity> readOrderList(){
        return orderRepository.findAll();
    }
    //update
    public Optional<OrderEntity> updateOrder(OrderEntity orderEntity, Long id){
        OrderEntity orderEntityDB = orderRepository.findById(id).get();
        if (!"".equalsIgnoreCase(orderEntity.getCustomerName())){
            orderEntityDB.setCustomerName(orderEntity.getCustomerName());
        }
        if (Objects.nonNull(orderEntity.getOrderDate())){
            orderEntityDB.setOrderDate(orderEntity.getOrderDate());
        }
        if (!"".equalsIgnoreCase(orderEntity.getShippingAddress())){
            orderEntityDB.setShippingAddress(orderEntity.getShippingAddress());
        }
        if (Objects.nonNull(orderEntity.getTotal())){
            orderEntityDB.setTotal(orderEntity.getTotal());
        }
        return Optional.of(orderRepository.save(orderEntityDB));
    }
    public boolean deleteOrderById(Long id){
        if (orderRepository.findById(id).isPresent()){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
