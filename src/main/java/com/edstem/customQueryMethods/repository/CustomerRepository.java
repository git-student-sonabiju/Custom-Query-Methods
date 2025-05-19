package com.edstem.customQueryMethods.repository;

import com.edstem.customQueryMethods.dto.TopCustomerDTO;
import com.edstem.customQueryMethods.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT c.name AS name,SUM(oi.quantity * oi.price) AS totalSpent " +
			"FROM Customer c JOIN c.orders o JOIN o.items oi " +
			"GROUP BY c.name ORDER BY totalSpent DESC")
	List<TopCustomerDTO> findTopSpendingCustomers();
}
