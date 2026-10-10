package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements IJugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public Jugador guardar(Jugador jugador) {

        try {

            if (jugador.getIdJugador() != null && jugador.getIdJugador() > 0) {
                jugadorRepository.actualizarJugador(jugador);
            } else {

                jugadorRepository.registrarJugador(jugador);
            }
            return jugador;
        } catch (Exception e) {
            throw new RuntimeException("Error en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public List<Jugador> listarTodos() {
        try {

            return jugadorRepository.listarJugadores();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar: " + e.getMessage());
        }
    }

    @Override
    public Optional<Jugador> buscarPorId(Integer id) {

        return Optional.empty();
    }

    @Override
    public void eliminar(Integer id) {
        try {

            jugadorRepository.eliminarJugador(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar: " + e.getMessage());
        }
    }
}