package com.edstem.customQueryMethods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Long id;
	private LocalDateTime orderDate;
	private Long customer_id;
}
