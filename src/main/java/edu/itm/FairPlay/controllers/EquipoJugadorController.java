package edu.itm.FairPlay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.services.EquipoJugadorService; // O IEquipoJugadorService si tienes la interfaz
import java.util.List;

@RestController
@RequestMapping("/api/equipo-jugadores")
public class EquipoJugadorController implements IEquipoJugadorController {

    private final EquipoJugadorService servicio; // Cambia a IEquipoJugadorService si usas la interfaz

    public EquipoJugadorController(EquipoJugadorService servicio) {
        this.servicio = servicio;
    }

    @Override
    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<?> obtenerJugadoresPorEquipo(@PathVariable Integer idEquipo) {
        try {
            List<EquipoJugador> jugadores = servicio.listarPorEquipo(idEquipo);
            return ResponseEntity.ok(jugadores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al obtener los jugadores del equipo: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> agregarJugadorAEquipo(@RequestBody EquipoJugador equipoJugador) {
        try {
            servicio.registrarEquipoJugador(equipoJugador);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Listo! El jugador fue añadido al equipo correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al agregar el jugador al equipo: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/equipo/{idEquipo}/jugador/{idJugador}")
    public ResponseEntity<?> eliminarJugadorDeEquipo(@PathVariable Integer idEquipo, @PathVariable Integer idJugador) {
        try {
            servicio.eliminarEquipoJugador(idEquipo, idJugador);
            return ResponseEntity.ok("¡El jugador fue retirado del equipo exitosamente!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación al retirar jugador: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}