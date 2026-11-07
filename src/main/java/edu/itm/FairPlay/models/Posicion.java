package edu.itm.FairPlay.models;

import com.fasterxml.jackson.annotation.JsonIgnore; // Importante importar esta anotación
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Posicion")
public class Posicion {

    @Id
    @Column(name = "idPosicion")
    private Integer idPosicion;

    @Column(name = "nombrePosicion", length = 50)
    private String nombrePosicion;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    // Relación inversa opcional OneToMany
    @OneToMany(mappedBy = "posicion", cascade = CascadeType.ALL)
    @JsonIgnore // <-- AGREGAR AQUÍ: Evita que al convertir a JSON entre en bucle infinito
    private List<Jugador> jugadores;

    public Posicion() {}

    // Getters y Setters
    public Integer getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(Integer idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getNombrePosicion() {
        return nombrePosicion;
    }

    public void setNombrePosicion(String nombrePosicion) {
        this.nombrePosicion = nombrePosicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}