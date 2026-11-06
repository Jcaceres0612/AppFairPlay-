
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

    // Corregido con el nombre exacto que vimos en SQL Server
    @Column(name = "nombreHabilidad")
    private String nombreHabilidad;
}