package com.example.seguimiento_productos_mascotas.controller;

import com.example.seguimiento_productos_mascotas.model.Ubicacion;
import com.example.seguimiento_productos_mascotas.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionService.getAllUbicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getUbicacionById(@PathVariable Long id) {
        return ubicacionService.getUbicacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ubicacion createUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.saveUbicacion(ubicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable Long id, @RequestBody Ubicacion ubicacionDetails) {
        return ubicacionService.getUbicacionById(id)
                .map(ubicacion -> {
                    ubicacion.setCiudad(ubicacionDetails.getCiudad());
                    ubicacion.setDireccion(ubicacionDetails.getDireccion());
                    return ResponseEntity.ok(ubicacionService.saveUbicacion(ubicacion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        return ubicacionService.getUbicacionById(id)
                .map(ubicacion -> {
                    ubicacionService.deleteUbicacion(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}