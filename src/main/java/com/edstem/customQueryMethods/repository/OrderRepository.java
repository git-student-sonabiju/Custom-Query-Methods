package com.edstem.customQueryMethods.repository;

import com.edstem.customQueryMethods.dto.AverageOrderDTO;
import com.edstem.customQueryMethods.dto.SalesByPeriodDTO;
import com.edstem.customQueryMethods.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT TO_CHAR(o.order_date, 'YYYY-MM') AS period, " +
			"SUM(oi.quantity * oi.price) AS totalSales " +
			"FROM orders o JOIN order_item oi ON o.id = oi.order_id " +
			"GROUP BY period ORDER BY period", nativeQuery = true)
	List<SalesByPeriodDTO> findSalesPeriod();

	@Query("SELECT AVG(oi.quantity * oi.price) AS averageValue " +
			"FROM Order o JOIN o.items oi")
	List<AverageOrderDTO> findAverageOrder();
}
