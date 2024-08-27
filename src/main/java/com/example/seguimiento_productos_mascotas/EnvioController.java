package com.example.seguimiento_productos_mascotas;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private List<Envio> envios = new ArrayList<>();

    public EnvioController() {
        envios.add(new Envio("ENV123", "Alimento para perros", new Date(), new Ubicacion("Santiago", "Calle 123")));
        envios.add(new Envio("ENV124", "Juguete para gatos", new Date(), new Ubicacion("Valparaíso", "Calle 456")));
    }

    @GetMapping
    public List<Envio> getEnvios() {
        return envios;
    }

    @GetMapping("/{idEnvio}")
    public Envio getEnvioById(@PathVariable String idEnvio) {
        return envios.stream().filter(envio -> envio.getIdEnvio().equals(idEnvio)).findFirst().orElse(null);
    }

    @GetMapping("/{idEnvio}/ubicacion")
    public Ubicacion getUbicacionActual(@PathVariable String idEnvio) {
        Envio envio = envios.stream().filter(e -> e.getIdEnvio().equals(idEnvio)).findFirst().orElse(null);
        return envio != null ? envio.getUbicacionActual() : null;
    }

    @PostMapping
    public Envio crearEnvio(@RequestBody Envio envio) {
        envios.add(envio);
        return envio;
    }
}
