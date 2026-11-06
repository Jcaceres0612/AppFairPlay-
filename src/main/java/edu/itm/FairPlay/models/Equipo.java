package edu.itm.FairPlay.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipo")
    private Integer idEquipo;

    @Column(name = "nombreEquipo", nullable = false, length = 100)
    private String nombreEquipo;

    // Relación ManyToOne con Partido (refleja la FK idPartido)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPartido", referencedColumnName = "idPartido", nullable = false)
    private Partido partido;

    public Equipo() {}

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}