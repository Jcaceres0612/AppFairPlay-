package edu.itm.FairPlay.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Equipo {
    private Integer idEquipo;
    private String nombreEquipo;

    // Llave foránea para relacionar el equipo con el partido
    private Integer idPartido;
}