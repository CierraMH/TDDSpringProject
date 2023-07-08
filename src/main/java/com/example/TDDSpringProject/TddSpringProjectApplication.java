package com.example.TDDSpringProject;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TddSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddSpringProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(OrderServices orderServices){
		return (args -> {
			insertTest(orderServices);
			//System.out.println(orderServices.readOrderList());
		});
	}
	private void insertTest(OrderServices orderServices){
		//orderServices.createOrder(new OrderEntity("CustomerName",LocalDate.now(),"123 Main Plano,Tx 75025",11.00));
		//orderServices.deleteOrderById(Long.valueOf(1552));
		//orderServices.updateOrder(new OrderEntity("Updated CustomerName",LocalDate.now(),"123 New Main Plano,Tx 75025",12.00),Long.valueOf(1602));
		}

}
