package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.JugadorHabilidad;
import edu.itm.FairPlay.services.JugadorHabilidadService;
import java.util.List;

@RestController
@RequestMapping("/api/jugador-habilidades")
public class JugadorHabilidadController {

    private final JugadorHabilidadService servicio;

    public JugadorHabilidadController(JugadorHabilidadService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> asociarHabilidadAJugador(@RequestBody JugadorHabilidad jugadorHabilidad) {
        try {
            servicio.registrarJugadorHabilidad(jugadorHabilidad);
            return ResponseEntity.ok("¡Listo! La valoración de la habilidad fue asignada al jugador.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al asociar: " + e.getMessage());
        }
    }

    @GetMapping("/jugador/{idJugador}")
    public List<JugadorHabilidad> obtenerHabilidadesPorJugador(@PathVariable Integer idJugador) {
        try {
            return servicio.listarPorJugador(idJugador);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/jugador/{idJugador}/habilidad/{idHabilidad}")
    public ResponseEntity<String> eliminarHabilidadDeJugador(@PathVariable Integer idJugador, @PathVariable Integer idHabilidad) {
        try {
            servicio.eliminarJugadorHabilidad(idJugador, idHabilidad);
            return ResponseEntity.ok("¡La habilidad del jugador fue removida con éxito!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}