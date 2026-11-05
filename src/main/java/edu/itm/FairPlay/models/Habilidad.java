package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Habilidad") // Nombre exacto de la tabla en SQL
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable
    @Column(name = "idHabilidad")
    private Integer idHabilidad;

    @Column(name = "nombre") // Enlaza la variable con la columna real de la BD
    private String nombreHabilidad;
}