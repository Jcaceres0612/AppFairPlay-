package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Equipo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Equipos", description = "Manejo de los equipos inscritos en el torneo")
public interface IEquipoController {

    @Operation(summary = "Ver todos", description = "Trae la lista de equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    ResponseEntity<?> listarEquipos();

    @Operation(summary = "Registrar equipo", description = "Guarda un equipo nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    ResponseEntity<?> registrarEquipo(@RequestBody Equipo equipo);

    @Operation(summary = "Actualizar equipo", description = "Modifica un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipo equipo);

    @Operation(summary = "Borrar equipo", description = "Elimina un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminarEquipo(@PathVariable Integer id);
}