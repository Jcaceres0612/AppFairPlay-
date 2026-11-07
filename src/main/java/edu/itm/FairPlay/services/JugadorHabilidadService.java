package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.JugadorHabilidad;
import edu.itm.FairPlay.repositories.JugadorHabilidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorHabilidadService implements IJugadorHabilidadService {

    private final JugadorHabilidadRepository jugadorHabilidadRepository;

    public JugadorHabilidadService(JugadorHabilidadRepository jugadorHabilidadRepository) {
        this.jugadorHabilidadRepository = jugadorHabilidadRepository;
    }

    @Override
    public void registrarJugadorHabilidad(JugadorHabilidad jugadorHabilidad) throws Exception {
        if (jugadorHabilidad.getValor() == null || jugadorHabilidad.getValor() < 0) {
            throw new IllegalArgumentException("El valor de la habilidad debe ser un número válido.");
        }
        jugadorHabilidadRepository.registrarJugadorHabilidad(jugadorHabilidad);
    }

    @Override
    public List<JugadorHabilidad> listarJugadorHabilidades() throws Exception {
        return jugadorHabilidadRepository.listarJugadorHabilidades();
    }

    @Override
    public JugadorHabilidad buscarPorId(Integer id) throws Exception {
        JugadorHabilidad jh = jugadorHabilidadRepository.buscarPorId(id);
        if (jh == null) {
            throw new RecursoNoEncontradoException("La relación Jugador-Habilidad con ID " + id + " no fue encontrada.");
        }
        return jh;
    }

    @Override
    public void actualizarJugadorHabilidad(JugadorHabilidad jugadorHabilidad) throws Exception {
        jugadorHabilidadRepository.actualizarJugadorHabilidad(jugadorHabilidad);
    }

    @Override
    public void eliminarJugadorHabilidad(Integer id) throws Exception {
        jugadorHabilidadRepository.eliminarJugadorHabilidad(id);
    }
}