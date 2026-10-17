package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.services.IPosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posiciones")
public class PosicionController implements IPosicionController {

    @Autowired
    private IPosicionService posicionService;

    @Override
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(posicionService.listarPosiciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al obtener la lista de posiciones: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> registrarPosicion(@RequestBody Posicion posicion) {
        try {
            posicionService.registrarPosicion(posicion);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Listo! La posición fue guardada correctamente en la base de datos de FairPlay.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al registrar la posición: " + e.getMessage());
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPosicion(@PathVariable Integer id, @RequestBody Posicion posicion) {
        try {
            posicion.setIdPosicion(id);
            posicionService.actualizarPosicion(posicion);
            return ResponseEntity.ok("¡La posición con ID " + id + " fue actualizada exitosamente!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al actualizar la posición: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPosicion(@PathVariable Integer id) {
        try {
            posicionService.eliminarPosicion(id);
            return ResponseEntity.ok("La posición con ID " + id + " ha sido eliminada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la posición: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al eliminar la posición: " + e.getMessage());
        }
    }
}