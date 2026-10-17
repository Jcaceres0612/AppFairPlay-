package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.JugadorHabilidad;
import edu.itm.FairPlay.services.IJugadorHabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugador-habilidades")
public class JugadorHabilidadController implements IJugadorHabilidadController {

    @Autowired
    private IJugadorHabilidadService servicio;

    @Override
    @GetMapping
    public ResponseEntity<?> listarHabilidadesDeJugadores() {
        try {
            return ResponseEntity.ok(servicio.listarHabilidadesDeJugadores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las habilidades de los jugadores: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> asignarHabilidadAJugador(@RequestBody JugadorHabilidad jugadorHabilidad) {
        try {
            servicio.asignarHabilidadAJugador(jugadorHabilidad);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Listo! La valoración de la habilidad fue asignada al jugador.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al asociar: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHabilidadDeJugador(@PathVariable Integer id) {
        try {
            servicio.eliminarHabilidadDeJugador(id);
            return ResponseEntity.ok("¡La habilidad del jugador fue removida con éxito!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la habilidad: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}