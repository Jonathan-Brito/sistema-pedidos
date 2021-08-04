package com.brito.sistemapedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.sistemapedidos.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

}
