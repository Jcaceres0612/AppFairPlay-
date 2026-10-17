package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Partido;
import edu.itm.FairPlay.services.PartidoService;
import java.util.List;

//@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService servicio;

    public PartidoController(PartidoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> crearPartido(@RequestBody Partido partido) {
        try {
            servicio.registrarPartido(partido);
            return ResponseEntity.ok("¡Listo! El partido fue programado correctamente en FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al crear el partido: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Partido> obtenerTodosLosPartidos() {
        try {
            return servicio.listarPartidos();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPartido(@PathVariable Integer id, @RequestBody Partido partido) {
        try {
            partido.setIdPartido(id);
            servicio.actualizarPartido(partido);
            return ResponseEntity.ok("¡El partido con ID " + id + " fue actualizado exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPartido(@PathVariable Integer id) {
        try {
            servicio.eliminarPartido(id);
            return ResponseEntity.ok("¡El partido con ID " + id + " fue eliminado exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}