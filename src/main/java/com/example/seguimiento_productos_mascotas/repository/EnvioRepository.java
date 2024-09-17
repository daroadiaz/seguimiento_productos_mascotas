package com.example.seguimiento_productos_mascotas.repository;

import com.example.seguimiento_productos_mascotas.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
}