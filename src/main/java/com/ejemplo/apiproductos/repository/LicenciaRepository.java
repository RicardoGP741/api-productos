package com.ejemplo.apiproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.apiproductos.model.Licencia;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
}
