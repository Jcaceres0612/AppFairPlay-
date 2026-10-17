package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.services.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController implements IEquipoController {

    @Autowired
    private IEquipoService equipoService;

    @Override
    @GetMapping
    public ResponseEntity<?> listarEquipos() {
        try {
            return ResponseEntity.ok(equipoService.listarEquipos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al obtener la lista de equipos: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> registrarEquipo(@RequestBody Equipo equipo) {
        try {
            equipoService.registrarEquipo(equipo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Listo! El equipo fue guardado correctamente en la base de datos de FairPlay.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al registrar el equipo: " + e.getMessage());
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipo equipo) {
        try {
            equipo.setIdEquipo(id);
            equipoService.actualizarEquipo(equipo);
            return ResponseEntity.ok("¡El equipo con ID " + id + " fue actualizado exitosamente en FairPlay!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al actualizar el equipo: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Integer id) {
        try {
            equipoService.eliminarEquipo(id);
            return ResponseEntity.ok("El equipo con ID " + id + " ha sido eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el equipo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al eliminar el equipo: " + e.getMessage());
        }
    }
}