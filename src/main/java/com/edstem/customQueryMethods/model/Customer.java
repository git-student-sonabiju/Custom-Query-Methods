package com.edstem.customQueryMethods.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String contactInformation;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;

}
