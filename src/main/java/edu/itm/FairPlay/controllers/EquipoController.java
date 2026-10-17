package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.services.EquipoService; // Cambiado para usar tu clase exacta
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController implements IEquipoController {
    @Autowired
    private EquipoService equipoService; // Cambiado aquí también

    @GetMapping
     public ResponseEntity<?> listarEquipos() {
        try {
            return ResponseEntity.ok(equipoService.listarEquipos());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
     public ResponseEntity<?> registrarEquipo(@RequestBody Equipo equipo) {
        try {
            equipoService.registrarEquipo(equipo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("¡Listo! El equipo fue guardado correctamente en la base de datos de FairPlay.");
    }

    @PutMapping("/{id}")
     public ResponseEntity<?> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipo equipo) {
        equipo.setIdEquipo(id);
        try {
            equipoService.actualizarEquipo(equipo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("¡El equipo con ID " + id + " fue actualizado exitosamente en FairPlay!");
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<?> eliminarEquipo(@PathVariable Integer id) {
        try {
            equipoService.eliminarEquipo(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("El equipo con ID " + id + " ha sido eliminado correctamente.");
    }
}