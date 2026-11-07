package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.JugadorHabilidad;
import java.util.List;

public interface IJugadorHabilidadService {
    void registrarJugadorHabilidad(JugadorHabilidad jugadorHabilidad) throws Exception;
    List<JugadorHabilidad> listarJugadorHabilidades() throws Exception;
    JugadorHabilidad buscarPorId(Integer id) throws Exception;
    void actualizarJugadorHabilidad(JugadorHabilidad jugadorHabilidad) throws Exception;
    void eliminarJugadorHabilidad(Integer id) throws Exception;
}