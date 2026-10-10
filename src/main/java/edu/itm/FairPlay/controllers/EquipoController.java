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
@Tag(name = "Equipos", description = "Manejo de los equipos inscritos en el torneo")
public class EquipoController {

    @Autowired
    private EquipoService equipoService; // Cambiado aquí también

    @GetMapping
    @Operation(summary = "Ver todos los equipos", description = "Trae la lista completa de los equipos registrados.")
    public ResponseEntity<?> listarEquipos() {
        try {
            return ResponseEntity.ok(equipoService.listarEquipos());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un equipo nuevo", description = "Pilas: No le mandes idEquipo para que se cree solo. Pero SÍ tienes que mandarle un idPartido que ya exista previamente en la base de datos.")
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
    @Operation(summary = "Actualizar datos del equipo", description = "Modifica un equipo existente mandándole su ID exacto en la URL.")
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
    @Operation(summary = "Borrar un equipo", description = "Elimina el equipo de la base de datos. (Recuerda revisar que no tenga jugadores amarrados antes de borrarlo).")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Integer id) {
        try {
            equipoService.eliminarEquipo(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("El equipo con ID " + id + " ha sido eliminado correctamente.");
    }
}