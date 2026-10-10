package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.JugadorHabilidad;
import java.util.List;

public interface JugadorHabilidadRepository {
    void registrarJugadorHabilidad(JugadorHabilidad jugadorHabilidad) throws Exception;
    List<JugadorHabilidad> listarPorJugador(Integer idJugador) throws Exception;
    void eliminarJugadorHabilidad(Integer idJugador, Integer idHabilidad) throws Exception;
}