package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.JugadorHabilidad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Jugadores - Habilidades", description = "Manejo de la asignación de habilidades a los jugadores")
public interface IJugadorHabilidadController {

    @Operation(summary = "Ver todas las habilidades de los jugadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    ResponseEntity<?> listarHabilidadesDeJugadores();

    @Operation(summary = "Asignar habilidad a jugador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habilidad asignada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    ResponseEntity<?> asignarHabilidadAJugador(@RequestBody JugadorHabilidad jugadorHabilidad);

    @Operation(summary = "Eliminar habilidad de un jugador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidad eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminarHabilidadDeJugador(@PathVariable Integer id);
}