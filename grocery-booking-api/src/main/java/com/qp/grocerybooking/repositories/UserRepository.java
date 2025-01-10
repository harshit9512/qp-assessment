package com.qp.grocerybooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.grocerybooking.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
