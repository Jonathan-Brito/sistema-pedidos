package com.brito.sistemapedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.sistemapedidos.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
