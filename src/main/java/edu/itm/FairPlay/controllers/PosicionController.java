package edu.itm.FairPlay.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.services.PosicionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/posiciones")
@Tag(name = "Posiciones", description = "Configuración de las posiciones en la cancha (Arquero, Defensa, etc.)")
public class PosicionController {

    @Autowired
    private PosicionService posicionService;

    @GetMapping
    @Operation(summary = "Ver todas las posiciones", description = "Trae la lista de las posiciones que pueden jugar en la cancha.")
    public ResponseEntity<?> listarTodos() throws Exception {
        return ResponseEntity.ok(posicionService.listarPosiciones());
    } // <--- Ya te puse la llave de cierre que faltaba aquí

    @PostMapping
    @Operation(summary = "Crear una posición nueva", description = "Ojo: A diferencia de los jugadores, aquí SÍ tienes que mandar el idPosicion en el JSON porque la base de datos no lo genera automático.")
    public ResponseEntity<?> registrarPosicion(@RequestBody Posicion posicion) throws Exception { // <--- Le quité el punto y coma (;)
        posicionService.registrarPosicion(posicion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("¡Listo! La posición fue guardada correctamente en la base de datos de FairPlay.");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una posición", description = "Le cambias el nombre o la descripción a una posición que ya existe.")
    public ResponseEntity<?> actualizarPosicion(@PathVariable Integer id, @RequestBody Posicion posicion) throws Exception {
        posicion.setIdPosicion(id);
        posicionService.actualizarPosicion(posicion);
        return ResponseEntity.ok("¡La posición con ID " + id + " fue actualizada exitosamente!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar una posición", description = "Pilas: Si hay jugadores usando esta posición, SQL Server no te va a dejar borrarla para proteger los datos.")
    public ResponseEntity<?> eliminarPosicion(@PathVariable Integer id) throws Exception {
        posicionService.eliminarPosicion(id);
        return ResponseEntity.ok("La posición con ID " + id + " ha sido eliminada correctamente.");
    }
}