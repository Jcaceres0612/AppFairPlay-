package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.services.PosicionService;
import java.util.List;

@RestController
@RequestMapping("/api/posiciones")
public class PosicionController {

    private final PosicionService servicio;

    public PosicionController(PosicionService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> crearPosicion(@RequestBody Posicion posicion) {
        try {
            servicio.registrarPosicion(posicion);
            return ResponseEntity.ok("¡Listo! La posición fue guardada correctamente en FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al guardar: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Posicion> obtenerTodasLasPosiciones() {
        try {
            return servicio.listarPosiciones();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPosicion(@PathVariable Integer id, @RequestBody Posicion posicion) {
        try {
            posicion.setIdPosicion(id);
            servicio.actualizarPosicion(posicion);
            return ResponseEntity.ok("¡La posición con ID " + id + " fue actualizada exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPosicion(@PathVariable Integer id) {
        try {
            servicio.eliminarPosicion(id);
            return ResponseEntity.ok("¡La posición con ID " + id + " fue eliminada exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}