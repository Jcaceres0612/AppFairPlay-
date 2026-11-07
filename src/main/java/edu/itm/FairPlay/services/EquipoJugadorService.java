package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.repositories.EquipoJugadorRepository;
import edu.itm.FairPlay.repositories.EquipoRepository;
import edu.itm.FairPlay.repositories.JugadorJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoJugadorService implements IEquipoJugadorService {

    private final EquipoJugadorRepository equipoJugadorRepo;
    private final EquipoRepository equipoRepo;
    private final JugadorJpaRepository jugadorRepo;

    public EquipoJugadorService(EquipoJugadorRepository equipoJugadorRepo,
                                EquipoRepository equipoRepo,
                                JugadorJpaRepository jugadorRepo) {
        this.equipoJugadorRepo = equipoJugadorRepo;
        this.equipoRepo = equipoRepo;
        this.jugadorRepo = jugadorRepo;
    }

    @Override
    public void asociarJugadorAEquipo(EquipoJugador equipoJugador) throws Exception {

        // 1. Validar que los IDs no vengan nulos
        if (equipoJugador.getIdJugador() == null || equipoJugador.getIdEquipo() == null) {
            throw new IllegalArgumentException("Error: El ID del jugador y del equipo son obligatorios.");
        }

        // 2. Validar que los IDs sean positivos
        if (equipoJugador.getIdJugador() <= 0 || equipoJugador.getIdEquipo() <= 0) {
            throw new IllegalArgumentException("Error: Los IDs deben ser números positivos mayores a cero.");
        }

        // 3. Validar que el jugador exista
        if (!jugadorRepo.existsById(equipoJugador.getIdJugador())) {
            throw new RecursoNoEncontradoException(
                    "No se puede asociar: El jugador con ID " + equipoJugador.getIdJugador() + " no existe.");
        }

        // 4. Validar que el equipo exista
        Equipo equipoEncontrado = equipoRepo.buscarPorId(equipoJugador.getIdEquipo());
        if (equipoEncontrado == null) {
            throw new RecursoNoEncontradoException(
                    "No se puede asociar: El equipo con ID " + equipoJugador.getIdEquipo() + " no existe.");
        }

        // Si pasa todas las validaciones, guardamos
        equipoJugadorRepo.registrarEquipoJugador(equipoJugador);
    }

    @Override
    public List<EquipoJugador> listarEquiposJugadores() throws Exception {
        return equipoJugadorRepo.listarEquipoJugadores();
    }

    @Override
    public void desasociarJugadorDeEquipo(Integer id) throws Exception {
        // Lógica futura
    }
}