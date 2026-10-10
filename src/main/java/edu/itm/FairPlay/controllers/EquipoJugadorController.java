package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.services.EquipoJugadorService;
import java.util.List;

//@RestController
@RequestMapping("/api/equipo-jugadores")
public class EquipoJugadorController {

    private final EquipoJugadorService servicio;

    public EquipoJugadorController(EquipoJugadorService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> agregarJugadorAEquipo(@RequestBody EquipoJugador equipoJugador) {
        try {
            servicio.registrarEquipoJugador(equipoJugador);
            return ResponseEntity.ok("¡Listo! El jugador fue añadido al equipo correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al agregar el jugador al equipo: " + e.getMessage());
        }
    }

    @GetMapping("/equipo/{idEquipo}")
    public List<EquipoJugador> obtenerJugadoresPorEquipo(@PathVariable Integer idEquipo) {
        try {
            return servicio.listarPorEquipo(idEquipo);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/equipo/{idEquipo}/jugador/{idJugador}")
    public ResponseEntity<String> eliminarJugadorDeEquipo(@PathVariable Integer idEquipo, @PathVariable Integer idJugador) {
        try {
            servicio.eliminarEquipoJugador(idEquipo, idJugador);
            return ResponseEntity.ok("¡El jugador fue retirado del equipo exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}
