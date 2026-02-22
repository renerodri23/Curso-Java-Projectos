package org.rplacios.java_jdbc.modelo;

public class Categoria {
    private Long id;
    private String nombre;

    @Override
    public String toString() {
        return id +
                " | " + nombre ;
    }

    public Categoria() {
    }

    public Categoria(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
