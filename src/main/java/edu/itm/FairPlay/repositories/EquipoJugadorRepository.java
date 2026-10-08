package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.EquipoJugador;
import java.util.List;

public interface EquipoJugadorRepository {
    void registrarEquipoJugador(EquipoJugador equipoJugador) throws Exception;
    List<EquipoJugador> listarPorEquipo(Integer idEquipo) throws Exception;
    void eliminarEquipoJugador(Integer idEquipo, Integer idJugador) throws Exception;
}