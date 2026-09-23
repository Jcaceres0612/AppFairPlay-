package edu.itm.FairPlay.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Jugador {
    private Integer idJugador;
    private String nombre;
    private String apellido;
    private Integer idPosicion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;
}