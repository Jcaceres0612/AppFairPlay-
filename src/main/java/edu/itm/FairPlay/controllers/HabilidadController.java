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

    // --- ¡AQUÍ ESTÁ EL MÉTODO NUEVO PARA BUSCAR POR ID! ---
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            // Si Fabri le puso otro nombre en el servicio (ej. buscarHabilidad), IntelliJ te pedirá cambiarlo
            return new ResponseEntity<>(habilidadService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // --------------------------------------------------------

    @Override
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Habilidad habilidad) {
        try {
            habilidadService.registrarHabilidad(habilidad);
            return ResponseEntity.status(HttpStatus.CREATED).body("¡Habilidad guardada con éxito!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Habilidad habilidadActualizada) {
        try {
            Habilidad habilidadGuardada = habilidadService.actualizarHabilidad(id, habilidadActualizada);
            return new ResponseEntity<>(habilidadGuardada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            habilidadService.eliminarHabilidad(id);
            return ResponseEntity.ok("Habilidad eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar la habilidad: " + e.getMessage());
        }
    }
}