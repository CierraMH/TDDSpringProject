package com.example.TDDSpringProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
@SpringBootTest
public class HTTPTests {
    @Autowired private MockMvc mockMvc;
    @MockBean
    private OrderServices orderServices;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllOrdersApi() throws Exception{
        OrderEntity order = new OrderEntity("Create1CustomerName", LocalDate.now(), "123 Main st, Plano, Texas 75035", 10.00);
        Mockito.when(orderServices.readOrderList()).thenReturn(List.of(order));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createOrderApi() throws Exception {
        OrderEntity order = new OrderEntity("CreateCustomerName", LocalDate.now(), "123 Main st, Plano, Texas 75035", 10.00);
        Mockito.when(orderServices.createOrder(Mockito.any())).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/create")
                        .content(asJsonString(order))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdateApi() throws Exception {
        OrderEntity updateOrder = new OrderEntity("2Customer2Name", LocalDate.now(), "234 Main st, Plano, Texas 75035", 11.00);
        Mockito.when(orderServices.updateOrder(Mockito.any(), Mockito.any())).thenReturn(Optional.of(updateOrder));
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/update/{id}", 1L)
                        .content(asJsonString(updateOrder))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteApi() throws Exception {
        Mockito.when(orderServices.deleteOrderById(Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }}
