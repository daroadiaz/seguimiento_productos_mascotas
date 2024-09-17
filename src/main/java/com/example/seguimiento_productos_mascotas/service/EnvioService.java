package com.example.seguimiento_productos_mascotas.service;

import com.example.seguimiento_productos_mascotas.model.Envio;
import com.example.seguimiento_productos_mascotas.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    public Optional<Envio> getEnvioById(Long id) {
        return envioRepository.findById(id);
    }

    public Envio saveEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public void deleteEnvio(Long id) {
        envioRepository.deleteById(id);
    }
}