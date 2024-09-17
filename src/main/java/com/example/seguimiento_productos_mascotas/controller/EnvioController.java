package com.example.seguimiento_productos_mascotas.controller;

import com.example.seguimiento_productos_mascotas.model.Envio;
import com.example.seguimiento_productos_mascotas.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable Long id) {
        return envioService.getEnvioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Envio createEnvio(@RequestBody Envio envio) {
        return envioService.saveEnvio(envio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateEnvio(@PathVariable Long id, @RequestBody Envio envioDetails) {
        return envioService.getEnvioById(id)
                .map(envio -> {
                    envio.setProducto(envioDetails.getProducto());
                    envio.setFechaEnvio(envioDetails.getFechaEnvio());
                    envio.setUbicacionActual(envioDetails.getUbicacionActual());
                    return ResponseEntity.ok(envioService.saveEnvio(envio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Long id) {
        return envioService.getEnvioById(id)
                .map(envio -> {
                    envioService.deleteEnvio(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}