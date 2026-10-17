package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.JugadorHabilidad;
import java.util.List;

public interface IJugadorHabilidadService {
    void asignarHabilidadAJugador(JugadorHabilidad jugadorHabilidad) throws Exception;
    List<JugadorHabilidad> listarHabilidadesDeJugadores() throws Exception;
    void eliminarHabilidadDeJugador(Integer id) throws Exception;
}