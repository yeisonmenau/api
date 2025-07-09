package com.pruebatecnica.api.repository;

import com.pruebatecnica.api.model.RegistroViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroViajeRepository extends JpaRepository<RegistroViaje, Long> {
}
