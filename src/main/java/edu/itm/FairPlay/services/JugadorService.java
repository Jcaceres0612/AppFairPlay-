package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.repositories.JugadorJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    private final JugadorJpaRepository jugadorRepository;

    // Inyección del nuevo repositorio JPA
    public JugadorService(JugadorJpaRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador registrarJugador(Jugador jugador) {
        // save() detecta que el ID es nuevo y ejecuta un INSERT automático
        return jugadorRepository.save(jugador);
    }

    public List<Jugador> listarJugadores() {
        // findAll() ejecuta el SELECT y mapea la lista automáticamente
        return jugadorRepository.findAll();
    }

    public Jugador buscarPorId(Integer id) throws Exception {
        // findById() devuelve un "Optional" (una caja fuerte que puede tener o no al jugador)
        Optional<Jugador> jugadorEncontrado = jugadorRepository.findById(id);

        if (jugadorEncontrado.isPresent()) {
            return jugadorEncontrado.get(); // Sacamos al jugador de la caja
        } else {
            throw new Exception("Jugador con ID " + id + " no encontrado.");
        }
    }

    public Jugador actualizarJugador(Integer id, Jugador jugadorActualizado) throws Exception {
        // 1. Primero verificamos que el jugador exista
        Jugador jugadorExistente = buscarPorId(id);

        // 2. Modificamos los datos en la memoria
        jugadorExistente.setNombre(jugadorActualizado.getNombre());
        jugadorExistente.setApellido(jugadorActualizado.getApellido());
        jugadorExistente.setIdPosicion(jugadorActualizado.getIdPosicion());
        jugadorExistente.setFechaRegistro(jugadorActualizado.getFechaRegistro());

        // 3. save() detecta que el objeto ya tiene un ID existente y ejecuta un UPDATE
        return jugadorRepository.save(jugadorExistente);
    }

    public void eliminarJugador(Integer id) throws Exception {
        // Reutilizamos el método buscar para validar que exista antes de intentar borrar
        Jugador jugadorExistente = buscarPorId(id);
        jugadorRepository.delete(jugadorExistente);
    }
}