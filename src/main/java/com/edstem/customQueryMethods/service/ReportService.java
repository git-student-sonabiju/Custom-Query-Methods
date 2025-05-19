package com.edstem.customQueryMethods.service;

import com.edstem.customQueryMethods.dto.*;
import com.edstem.customQueryMethods.repository.CustomerRepository;
import com.edstem.customQueryMethods.repository.OrderItemRepository;
import com.edstem.customQueryMethods.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	public ReportService(CustomerRepository customerRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
	}

	public List<TopCustomerDTO> getTopSpendingCustomer() {
		return customerRepository.findTopSpendingCustomers();
	}

	public List<PopularProductDTO> getPopularProducts() {
		return orderItemRepository.findPopularProducts();
	}

	public List<SalesByPeriodDTO> getSalesByPeriod() {
		return orderRepository.findSalesPeriod();
	}

	public List<AverageOrderDTO> getAverageOrder() {
		return orderRepository.findAverageOrder();
	}
}