package com.qp.grocerybooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qp.grocerybooking.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
