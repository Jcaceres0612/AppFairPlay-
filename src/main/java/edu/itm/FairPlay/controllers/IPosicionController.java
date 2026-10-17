package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Posicion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Posiciones", description = "Manejo de las posiciones de los jugadores en el campo")
public interface IPosicionController {

    @Operation(summary = "Ver todas las posiciones", description = "Trae la lista de todas las posiciones disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    ResponseEntity<?> listarTodos();

    @Operation(summary = "Registrar posición", description = "Guarda una nueva posición")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Posición guardada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    ResponseEntity<?> registrarPosicion(@RequestBody Posicion posicion);

    @Operation(summary = "Actualizar posición", description = "Modifica una posición existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posición actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> actualizarPosicion(@PathVariable Integer id, @RequestBody Posicion posicion);

    @Operation(summary = "Borrar posición", description = "Elimina una posición usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posición eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminarPosicion(@PathVariable Integer id);
}