package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.repositories.EquipoJugadorRepository;
import edu.itm.FairPlay.repositories.EquipoRepository;
import edu.itm.FairPlay.repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Obligatorio para que Spring sepa que este es el "Chef"
public class EquipoJugadorService implements IEquipoJugadorService { // Aquí declaramos el contrato

    private final EquipoJugadorRepository equipoJugadorRepo;
    private final EquipoRepository equipoRepo;
    private final JugadorRepository jugadorRepo;

    // Inyectamos los TRES repositorios en el constructor
    public EquipoJugadorService(EquipoJugadorRepository equipoJugadorRepo,
                                EquipoRepository equipoRepo,
                                JugadorRepository jugadorRepo) {
        this.equipoJugadorRepo = equipoJugadorRepo;
        this.equipoRepo = equipoRepo;
        this.jugadorRepo = jugadorRepo;
    }

    @Override
    public void asociarJugadorAEquipo(EquipoJugador equipoJugador) throws Exception {

        // 1. Validar que los IDs existan y no vengan nulos
        if (equipoJugador.getIdJugador() == null || equipoJugador.getIdEquipo() == null) {
            throw new IllegalArgumentException("Error: El ID del jugador y del equipo son obligatorios.");
        }

        // 2. Validar que los IDs sean lógicos.
        if (equipoJugador.getIdJugador() <= 0 || equipoJugador.getIdEquipo() <= 0) {
            throw new IllegalArgumentException("Error: Los IDs deben ser números positivos mayores a cero.");
        }

        // 3. Validar que el Jugador realmente exista en la base de datos
        Jugador jugadorEncontrado = jugadorRepo.buscarPorId(equipoJugador.getIdJugador());
        if (jugadorEncontrado == null) {
            throw new Exception("No se puede asociar: El jugador con ID " + equipoJugador.getIdJugador() + " no existe.");
        }

        // 4. Validar que el Equipo realmente exista
        Equipo equipoEncontrado = equipoRepo.buscarPorId(equipoJugador.getIdEquipo());
        if (equipoEncontrado == null) {
            throw new Exception("No se puede asociar: El equipo con ID " + equipoJugador.getIdEquipo() + " no existe.");
        }

        // Si sobrevive a todo lo anterior, ¡guardamos!
        equipoJugadorRepo.registrarEquipoJugador(equipoJugador);
    } // <-- Aquí es donde realmente se cierra el método

    @Override
    public List<EquipoJugador> listarEquiposJugadores() throws Exception {
        return equipoJugadorRepo.listarPorEquipo(null);
    }

    @Override
    public void desasociarJugadorDeEquipo(Integer id) throws Exception {
        // Lógica futura
    }
}