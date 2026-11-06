package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.repositories.JugadorJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {

    private final JugadorJpaRepository jugadorRepository;

    public JugadorService(JugadorJpaRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador registrarJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public List<Jugador> listarJugadores() {
        return jugadorRepository.findAll();
    }

    public Jugador buscarPorId(Integer id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Jugador con ID " + id + " no encontrado."));
    }

    public Jugador actualizarJugador(Integer id, Jugador jugadorActualizado) {
        Jugador jugadorExistente = buscarPorId(id);

        jugadorExistente.setNombre(jugadorActualizado.getNombre());
        jugadorExistente.setApellido(jugadorActualizado.getApellido());
        jugadorExistente.setIdPosicion(jugadorActualizado.getIdPosicion());
        jugadorExistente.setFechaRegistro(jugadorActualizado.getFechaRegistro());

        return jugadorRepository.save(jugadorExistente);
    }

    public void eliminarJugador(Integer id) {
        Jugador jugadorExistente = buscarPorId(id);
        jugadorRepository.delete(jugadorExistente);
    }
}