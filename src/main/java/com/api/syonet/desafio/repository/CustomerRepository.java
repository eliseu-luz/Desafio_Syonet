package com.api.syonet.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.syonet.desafio.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository< Customer, Long > {
}
