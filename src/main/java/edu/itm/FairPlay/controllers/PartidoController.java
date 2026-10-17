package edu.itm.FairPlay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Partido;
import edu.itm.FairPlay.services.IPartidoService; // Cambiar a PartidoService si no usas interfaz en el Service
import java.util.List;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController implements IPartidoController {

    private final IPartidoService servicio; // Cambiar a PartidoService si no usas interfaz

    public PartidoController(IPartidoService servicio) { // Cambiar a PartidoService si no usas interfaz
        this.servicio = servicio;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> registrarPartido(@RequestBody Partido partido) {
        try {
            servicio.registrarPartido(partido);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Listo! El partido fue programado correctamente en FairPlay.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al crear el partido: " + e.getMessage());
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listarPartidos() {
        try {
            List<Partido> partidos = servicio.listarPartidos();
            return ResponseEntity.ok(partidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al obtener la lista de partidos: " + e.getMessage());
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPartido(@PathVariable Integer id, @RequestBody Partido partido) {
        try {
            partido.setIdPartido(id); // Asegúrate de que el modelo tenga setIdPartido
            servicio.actualizarPartido(partido);
            return ResponseEntity.ok("¡El partido con ID " + id + " fue actualizado exitosamente!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPartido(@PathVariable Integer id) {
        try {
            servicio.eliminarPartido(id);
            return ResponseEntity.ok("¡El partido con ID " + id + " fue eliminado exitosamente!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al eliminar: " + e.getMessage());
        }
    }
}