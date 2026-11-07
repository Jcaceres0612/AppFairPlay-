package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JugadorHabilidad")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorHabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugadorHabilidad")
    private Integer idJugadorHabilidad;

    @Column(name = "idJugador")
    private Integer idJugador;

    @Column(name = "idHabilidad")
    private Integer idHabilidad;

    @Column(name = "valor")
    private Integer valor; // Ejemplo: nivel o puntuación de la habilidad (1 a 100)
}