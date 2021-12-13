package com.company.a6recyclerview;

public class Campeon {
    String nombre;
    int imagen;
    String descripcion;
    float valoracion;

    public Campeon(String nombre, int imagen, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Campeon(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
