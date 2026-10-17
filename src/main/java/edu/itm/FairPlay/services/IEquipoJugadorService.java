package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.EquipoJugador;
import java.util.List;

public interface IEquipoJugadorService {
    void asociarJugadorAEquipo(EquipoJugador equipoJugador) throws Exception;
    List<EquipoJugador> listarEquiposJugadores() throws Exception;
    void desasociarJugadorDeEquipo(Integer id) throws Exception;
}