package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.services.HabilidadService;
import java.util.List;

//@RestController
@RequestMapping("/api/habilidades")
public class HabilidadController {

    private final HabilidadService servicio;

    public HabilidadController(HabilidadService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> crearHabilidad(@RequestBody Habilidad habilidad) {
        try {
            servicio.registrarHabilidad(habilidad);
            return ResponseEntity.ok("¡Listo! La habilidad fue guardada correctamente en FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al guardar: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Habilidad> obtenerTodasLasHabilidades() {
        try {
            return servicio.listarHabilidades();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHabilidad(@PathVariable Integer id, @RequestBody Habilidad habilidad) {
        try {
            habilidad.setIdHabilidad(id);
            servicio.actualizarHabilidad(habilidad);
            return ResponseEntity.ok("¡La habilidad con ID " + id + " fue actualizada exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabilidad(@PathVariable Integer id) {
        try {
            servicio.eliminarHabilidad(id);
            return ResponseEntity.ok("¡La habilidad con ID " + id + " fue eliminada exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}