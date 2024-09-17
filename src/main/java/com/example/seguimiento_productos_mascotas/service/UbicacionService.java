package com.example.seguimiento_productos_mascotas.service;

import com.example.seguimiento_productos_mascotas.model.Ubicacion;
import com.example.seguimiento_productos_mascotas.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> getUbicacionById(Long id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion saveUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public void deleteUbicacion(Long id) {
        ubicacionRepository.deleteById(id);
    }
}