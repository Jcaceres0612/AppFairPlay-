package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.repositories.EquipoJugadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

public class EquipoJugadorService {

    private final EquipoJugadorRepository repositorio;

    public EquipoJugadorService(EquipoJugadorRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarEquipoJugador(EquipoJugador equipoJugador) throws Exception {
        repositorio.registrarEquipoJugador(equipoJugador);
    }

    public List<EquipoJugador> listarPorEquipo(Integer idEquipo) throws Exception {
        return repositorio.listarPorEquipo(idEquipo);
    }

    public void eliminarEquipoJugador(Integer idEquipo, Integer idJugador) throws Exception {
        repositorio.eliminarEquipoJugador(idEquipo, idJugador);
    }
}