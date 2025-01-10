package com.qp.grocerybooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qp.grocerybooking.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
