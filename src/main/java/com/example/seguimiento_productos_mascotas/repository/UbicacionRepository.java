package com.example.seguimiento_productos_mascotas.repository;

import com.example.seguimiento_productos_mascotas.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
}