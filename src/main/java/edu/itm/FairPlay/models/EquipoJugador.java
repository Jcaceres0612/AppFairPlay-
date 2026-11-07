package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EquipoJugador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipoJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipoJugador")
    private Integer idEquipoJugador;

    @Column(name = "idEquipo")
    private Integer idEquipo;

    @Column(name = "idJugador")
    private Integer idJugador;
}