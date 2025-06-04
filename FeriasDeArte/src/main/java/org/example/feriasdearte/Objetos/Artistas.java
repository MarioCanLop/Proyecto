package org.example.feriasdearte.Objetos;

import lombok.Data;

@Data
public class Artistas {

    private int artistaid;
    private String nombre ;
    private String biografia;
    private String telefono ;
    private String email;

    public Artistas(int id, String nombre, String email, String telefono) {
        this.artistaid = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(int id, String nombre,  String biografia, String email, String telefono) {
        this.artistaid = id;
        this.nombre = nombre;
        this.biografia = biografia;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(String nombre, String biografia, String email, String telefono) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.email = email;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Artistas{" +
                "nombre='" + nombre + '\'' +
                ", biografia='" + biografia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
