package com.edstem.customQueryMethods.repository;

import com.edstem.customQueryMethods.dto.PopularProductDTO;
import com.edstem.customQueryMethods.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	@Query(value = "SELECT product_name AS productName, SUM(quantity) AS totalQuantity " +
			"FROM order_item GROUP BY product_name ORDER BY totalQuantity DESC", nativeQuery = true)
	List<PopularProductDTO> findPopularProducts();
}
