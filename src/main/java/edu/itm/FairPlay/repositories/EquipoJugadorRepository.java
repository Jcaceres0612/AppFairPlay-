package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.EquipoJugador;
import java.util.List;

public interface EquipoJugadorRepository {
    void registrarEquipoJugador(EquipoJugador equipoJugador) throws Exception;
    List<EquipoJugador> listarEquipoJugadores() throws Exception;
    EquipoJugador buscarPorId(Integer id) throws Exception;
    void actualizarEquipoJugador(EquipoJugador equipoJugador) throws Exception;
    void eliminarEquipoJugador(Integer id) throws Exception;
}