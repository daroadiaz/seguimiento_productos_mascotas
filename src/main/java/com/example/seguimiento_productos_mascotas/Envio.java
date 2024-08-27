package com.example.seguimiento_productos_mascotas;

import java.util.Date;

public class Envio {
    private String idEnvio;
    private String producto;
    private Date fechaEnvio;
    private Ubicacion ubicacionActual;

    public Envio(String idEnvio, String producto, Date fechaEnvio, Ubicacion ubicacionActual) {
        this.idEnvio = idEnvio;
        this.producto = producto;
        this.fechaEnvio = fechaEnvio;
        this.ubicacionActual = ubicacionActual;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Ubicacion getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(Ubicacion ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }
}
