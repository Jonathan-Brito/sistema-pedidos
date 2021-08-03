package com.brito.sistemapedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.sistemapedidos.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
