package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugador")
    private Integer idjugador;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;

    // Relación ManyToOne con Posicion (refleja la llave foránea idPosicion de tu SQL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPosicion", referencedColumnName = "idPosicion")
    private Posicion posicion;

    // Constructores vacíos y con parámetros
    public Jugador() {}

    public Jugador(Integer idjugador, String nombre, String apellido, LocalDate fechaRegistro, Posicion posicion) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
        this.posicion = posicion;
    }

    // Getters y Setters
    public Integer getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(Integer idjugador) {
        this.idjugador = idjugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}