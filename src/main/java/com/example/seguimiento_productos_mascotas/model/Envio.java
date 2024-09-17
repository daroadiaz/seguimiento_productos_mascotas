package com.example.seguimiento_productos_mascotas.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "envios")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    private Date fechaEnvio;
    
    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacionActual;

    public Envio() {}

    public Envio(Producto producto, Date fechaEnvio, Ubicacion ubicacionActual) {
        this.producto = producto;
        this.fechaEnvio = fechaEnvio;
        this.ubicacionActual = ubicacionActual;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public Ubicacion getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(Ubicacion ubicacionActual) { this.ubicacionActual = ubicacionActual; }
}