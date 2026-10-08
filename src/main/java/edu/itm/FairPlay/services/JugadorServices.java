package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServices {

    private final JugadorRepository repositorio;

    public JugadorServices(JugadorRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarJugador(Jugador jugador) throws Exception {
        repositorio.registrarJugador(jugador);
    }

    public List<Jugador> listarJugadores() throws Exception {
        return repositorio.listarJugadores();
    }

    public void actualizarJugador(Jugador jugador) throws Exception {
        repositorio.actualizarJugador(jugador);
    }

    public void eliminarJugador(Integer id) throws Exception {
        repositorio.eliminarJugador(id);
    }
}