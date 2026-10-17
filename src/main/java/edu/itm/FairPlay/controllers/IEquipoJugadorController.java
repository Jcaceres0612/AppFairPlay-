package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.EquipoJugador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Equipos - Jugadores", description = "Manejo de la asignación de jugadores a los equipos")
public interface IEquipoJugadorController {

    @Operation(summary = "Obtener jugadores de un equipo", description = "Trae la lista de jugadores que pertenecen a un equipo específico usando el ID del equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/equipo/{idEquipo}")
    ResponseEntity<?> obtenerJugadoresPorEquipo(@PathVariable Integer idEquipo);

    @Operation(summary = "Agregar jugador a un equipo", description = "Asocia un jugador a un equipo en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jugador agregado al equipo exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    ResponseEntity<?> agregarJugadorAEquipo(@RequestBody EquipoJugador equipoJugador);

    @Operation(summary = "Eliminar jugador de un equipo", description = "Retira a un jugador de un equipo usando ambos IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador retirado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/equipo/{idEquipo}/jugador/{idJugador}")
    ResponseEntity<?> eliminarJugadorDeEquipo(@PathVariable Integer idEquipo, @PathVariable Integer idJugador);
}