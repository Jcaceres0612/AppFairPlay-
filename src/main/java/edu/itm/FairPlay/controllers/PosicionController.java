package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.services.IPosicionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posiciones")
public class PosicionController implements IPosicionController {

    @Autowired
    private IPosicionService posicionService; // <-- Cambiado a la interfaz para mantener la consistencia

    @GetMapping
    public ResponseEntity<?> listarTodos() throws Exception {
        return ResponseEntity.ok(posicionService.listarPosiciones());
    }

    @PostMapping
    public ResponseEntity<?> registrarPosicion(@RequestBody Posicion posicion) throws Exception {
        posicionService.registrarPosicion(posicion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("¡Listo! La posición fue guardada correctamente en la base de datos de FairPlay.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPosicion(@PathVariable Integer id, @RequestBody Posicion posicion) throws Exception {
        posicion.setIdPosicion(id);
        posicionService.actualizarPosicion(posicion);
        return ResponseEntity.ok("¡La posición con ID " + id + " fue actualizada exitosamente!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPosicion(@PathVariable Integer id) throws Exception {
        posicionService.eliminarPosicion(id);
        return ResponseEntity.ok("La posición con ID " + id + " ha sido eliminada correctamente.");
    }
}