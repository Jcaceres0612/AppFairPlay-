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
    private IJugadorHabilidadService service;

    @Override
    @GetMapping
    public ResponseEntity<?> listarHabilidadesDeJugadores() {
        try {
            return ResponseEntity.ok(service.listarJugadorHabilidades());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las habilidades de los jugadores: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> asignarHabilidadAJugador(@RequestBody JugadorHabilidad jugadorHabilidad) {
        try {
            service.registrarJugadorHabilidad(jugadorHabilidad);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Habilidad asignada exitosamente al jugador!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody JugadorHabilidad jugadorHabilidad) {
        try {
            jugadorHabilidad.setIdJugadorHabilidad(id);
            service.actualizarJugadorHabilidad(jugadorHabilidad);
            return ResponseEntity.ok("¡Asignación de habilidad actualizada exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHabilidadDeJugador(@PathVariable Integer id) {
        try {
            service.eliminarJugadorHabilidad(id);
            return ResponseEntity.ok("Habilidad del jugador eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la habilidad: " + e.getMessage());
        }
    }
}