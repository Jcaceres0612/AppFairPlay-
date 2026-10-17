package edu.itm.FairPlay.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorHabilidad {
    private Integer idJugador;
    private Integer idHabilidad;
    private Integer valoracion;
}
