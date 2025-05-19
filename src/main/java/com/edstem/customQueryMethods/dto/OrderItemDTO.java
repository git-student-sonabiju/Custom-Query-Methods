package com.edstem.customQueryMethods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
	private Long id;
	private String productName;
	private Integer quantity;
	private Double price;
	private Long order_id;
}
