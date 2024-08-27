package com.example.seguimiento_productos_mascotas;

public class Ubicacion {
    private String ciudad;
    private String direccion;

    public Ubicacion(String ciudad, String direccion) {
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
