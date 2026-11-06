package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Partido")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPartido")
    private Integer idPartido;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "tipoPartido", nullable = false, length = 50)
    private String tipoPartido;

    @Column(name = "estado", length = 50)
    private String estado = "Programado";

    // Relación OneToMany inversa con Equipo
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    private List<Equipo> equipos;

    public Partido() {}

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoPartido() {
        return tipoPartido;
    }

    public void setTipoPartido(String tipoPartido) {
        this.tipoPartido = tipoPartido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}