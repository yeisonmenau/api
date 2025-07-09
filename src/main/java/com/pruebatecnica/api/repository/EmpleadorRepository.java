package com.pruebatecnica.api.repository;

import com.pruebatecnica.api.model.Empleador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadorRepository extends JpaRepository<Empleador, String> {
}
