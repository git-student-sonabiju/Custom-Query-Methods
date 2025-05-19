package com.edstem.customQueryMethods.controller;

import com.edstem.customQueryMethods.dto.*;
import com.edstem.customQueryMethods.model.Customer;
import com.edstem.customQueryMethods.model.Order;
import com.edstem.customQueryMethods.model.OrderItem;
import com.edstem.customQueryMethods.repository.CustomerRepository;
import com.edstem.customQueryMethods.repository.OrderItemRepository;
import com.edstem.customQueryMethods.repository.OrderRepository;
import com.edstem.customQueryMethods.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Validated
public class ReportController {
	private final ReportService reportService;
	private final CustomerRepository customerRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;

	public ReportController(ReportService reportService, CustomerRepository customerRepository, OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
		this.reportService = reportService;
		this.customerRepository = customerRepository;
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
	}

	@GetMapping("/top-customers")
	public List<TopCustomerDTO> getTopSpendingCustomer() {
		return reportService.getTopSpendingCustomer();
	}

	@GetMapping("/popular-products")
	public List<PopularProductDTO> getPopularProducts() {
		return reportService.getPopularProducts();
	}

	@GetMapping("/sales-period")
	public List<SalesByPeriodDTO> getSalesByPeriod() {
		return reportService.getSalesByPeriod();
	}

	@GetMapping("/avg-order")
	public List<AverageOrderDTO> getAverageOrder() {
		return reportService.getAverageOrder();
	}

	@PostMapping("/customers")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO customerDTO = new CustomerDTO(savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getContactInformation());
		return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
	}

	@PostMapping("/orders")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		Customer customer = customerRepository.findById(orderDTO.getCustomer_id())
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		Order order = new Order();
		order.setOrderDate(orderDTO.getOrderDate());
		order.setCustomer(customer);

		Order savedOrder = orderRepository.save(order);

		OrderDTO responseDTO = new OrderDTO(savedOrder.getId(), savedOrder.getOrderDate(), savedOrder.getCustomer().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}


	@PostMapping("/order-items")
	public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
		Order order = orderRepository.findById(orderItemDTO.getOrder_id())
				.orElseThrow(() -> new RuntimeException("Order not found"));

		OrderItem orderItem = new OrderItem();
		orderItem.setProductName(orderItemDTO.getProductName());
		orderItem.setQuantity(orderItemDTO.getQuantity());
		orderItem.setPrice(orderItemDTO.getPrice());
		orderItem.setOrder(order);

		OrderItem savedOrderItem = orderItemRepository.save(orderItem);

		OrderItemDTO responseDTO = new OrderItemDTO(savedOrderItem.getId(), savedOrderItem.getProductName(),
				savedOrderItem.getQuantity(), savedOrderItem.getPrice(), savedOrderItem.getOrder().getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}


}
