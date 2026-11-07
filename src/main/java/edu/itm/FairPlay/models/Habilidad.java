package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Habilidad")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHabilidad")
    private Integer idHabilidad;

    @Column(name = "nombreHabilidad")
    private String nombreHabilidad;

    // Agregamos este campo para que coincida con el repositorio y el servicio
    @Column(name = "descripcion")
    private String descripcion;
}