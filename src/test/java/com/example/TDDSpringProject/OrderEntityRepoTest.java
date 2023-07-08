package com.example.TDDSpringProject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class OrderEntityRepoTest {

    @Autowired
    public OrderRepository orderRepository;

    @Test
    void testCreateNewOrder(){
        OrderEntity orderEntity = new OrderEntity("CustomerCreate1Name",LocalDate.now(),"123 Main Plano,Tx 75025",11.00);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        Assertions.assertThat(orderEntity.getId()).isGreaterThan(0);
        Assertions.assertThat(orderEntity).isEqualTo(savedOrder);
    }
    @Test
    void testReadOrderList(){
        List<OrderEntity> orders = orderRepository.findAll();
        Assertions.assertThat(orders).isNotEmpty();
    }
    @Test
    void testUpdateOrder(){
        OrderEntity updateOrder = new OrderEntity("CustomerUpdateNewName",LocalDate.now(),"123 Main Plano,Tx 75025",11.00);
        orderRepository.save(updateOrder);
        OrderEntity readOrders = orderRepository.findById(updateOrder.getId()).orElse(null);
        Assertions.assertThat(readOrders).isNotNull();
        readOrders.setCustomerName("CustomerUpdateNewName");
        OrderEntity updatedOrder = orderRepository.save(readOrders);
        Assertions.assertThat(updatedOrder.getCustomerName()).isEqualTo("CustomerUpdateNewName");
    }
    @Test
    public void testDeleteOrderById(){
        OrderEntity order = new OrderEntity("CustomerDeleteName",LocalDate.now(),"123 Main Plano,Tx 75025",11.00);
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());
        Optional<OrderEntity> orderGone = orderRepository.findById(order.getId());
        Assertions.assertThat(orderGone).isEmpty();

    }
}
