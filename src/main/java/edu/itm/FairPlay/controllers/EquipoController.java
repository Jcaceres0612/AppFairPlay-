package edu.itm.FairPlay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.services.EquipoService;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService servicio;

    public EquipoController(EquipoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<String> crearEquipo(@RequestBody Equipo equipo) {
        try {
            servicio.registrarEquipo(equipo);
            return ResponseEntity.ok("¡Listo! El equipo fue guardado correctamente en FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al guardar: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Equipo> obtenerTodosLosEquipos() {
        try {
            return servicio.listarEquipos();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipo equipo) {
        try {
            equipo.setIdEquipo(id);
            servicio.actualizarEquipo(equipo);
            return ResponseEntity.ok("¡El equipo con ID " + id + " fue actualizado exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEquipo(@PathVariable Integer id) {
        try {
            servicio.eliminarEquipo(id);
            return ResponseEntity.ok("¡El equipo con ID " + id + " fue eliminado exitosamente!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}