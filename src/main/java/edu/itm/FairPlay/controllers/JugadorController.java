package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.services.IJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController implements IJugadorController {

    @Autowired
    private IJugadorService jugadorService;

    @Override
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(jugadorService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los jugadores: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Jugador jugador) {
        try {
            jugadorService.guardar(jugador);
            return ResponseEntity.status(HttpStatus.CREATED).body("¡Jugador guardado con éxito!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar el jugador: " + e.getMessage());
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador) {
        try {
            jugador.setIdJugador(id);
            jugadorService.guardar(jugador);
            return ResponseEntity.ok("Jugador actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar el jugador: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            jugadorService.eliminar(id);
            return ResponseEntity.ok("Jugador eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el jugador: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al eliminar el jugador: " + e.getMessage());
        }
    }
}