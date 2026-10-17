package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.JugadorHabilidad;
import edu.itm.FairPlay.repositories.JugadorHabilidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

public class JugadorHabilidadService {

    private final JugadorHabilidadRepository repositorio;

    public JugadorHabilidadService(JugadorHabilidadRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarJugadorHabilidad(JugadorHabilidad jugadorHabilidad) throws Exception {
        repositorio.registrarJugadorHabilidad(jugadorHabilidad);
    }

    public List<JugadorHabilidad> listarPorJugador(Integer idJugador) throws Exception {
        return repositorio.listarPorJugador(idJugador);
    }

    public void eliminarJugadorHabilidad(Integer idJugador, Integer idHabilidad) throws Exception {
        repositorio.eliminarJugadorHabilidad(idJugador, idHabilidad);
    }
}