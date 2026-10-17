package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.services.IHabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadController implements IHabilidadController {

    @Autowired
    private IHabilidadService habilidadService;

    @Override
    @GetMapping
    public ResponseEntity<?> obtenerTodasLasHabilidades() {
        try {
            return ResponseEntity.ok(habilidadService.listarHabilidades());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las habilidades: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Habilidad habilidad) {
        try {
            habilidadService.registrarHabilidad(habilidad);
            return ResponseEntity.status(HttpStatus.CREATED).body("¡Habilidad guardada con éxito!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar la habilidad: " + e.getMessage());
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Habilidad habilidad) {
        try {
            habilidad.setIdHabilidad(id);
            habilidadService.actualizarHabilidad(habilidad);
            return ResponseEntity.ok("Habilidad actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar la habilidad: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            habilidadService.eliminarHabilidad(id);
            return ResponseEntity.ok("Habilidad eliminada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar la habilidad: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al eliminar la habilidad: " + e.getMessage());
        }
    }
}