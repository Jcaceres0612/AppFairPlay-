package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.EquipoJugador;
import java.util.List;

public interface IEquipoJugadorService {

    void registrarEquipoJugador(EquipoJugador equipoJugador);

    List<EquipoJugador> listarPorEquipo(Integer idEquipo);

    void eliminarEquipoJugador(Integer idEquipo, Integer idJugador);
}