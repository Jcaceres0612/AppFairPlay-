package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.services.JugadorServices;
import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorServices servicio;

    // Se inyecta el Service en lugar del Repository
    public JugadorController(JugadorServices servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> crearJugador(@RequestBody Jugador jugador) {
        try {
            servicio.registrarJugador(jugador);
            return ResponseEntity.ok("¡Listo! El jugador fue guardado correctamente en la base de datos de FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.ok("Rayos, hubo un error al guardar: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Jugador> obtenerTodosLosJugadores() {
        try {
            return servicio.listarJugadores();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarJugador(@PathVariable Integer id, @RequestBody Jugador jugador) {
        try {
            jugador.setIdJugador(id);
            servicio.actualizarJugador(jugador);
            return ResponseEntity.ok("¡El jugador con ID " + id + " fue actualizado exitosamente en FairPlay!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarJugador(@PathVariable Integer id) {
        try {
            servicio.eliminarJugador(id);
            return ResponseEntity.ok("¡El jugador con ID " + id + " fue eliminado exitosamente de FairPlay!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}