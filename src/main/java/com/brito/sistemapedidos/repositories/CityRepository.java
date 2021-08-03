package com.brito.sistemapedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.sistemapedidos.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
