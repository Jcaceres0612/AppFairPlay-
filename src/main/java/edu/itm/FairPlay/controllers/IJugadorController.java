package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Jugador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Jugadores", description = "Todo lo que tiene que ver con los jugadores del torneo")
public interface IJugadorController {

    @Operation(summary = "Ver todos los jugadores", description = "Trae la lista completa de los jugadores registrados en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    ResponseEntity<?> listarTodos();

    @Operation(summary = "Registrar un jugador nuevo", description = "Ojo: No le mandes el idJugador en el JSON para que SQL Server le asigne el número automáticamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jugador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación (datos incorrectos o incompletos)"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al guardar")
    })
    @PostMapping
    ResponseEntity<?> guardar(@RequestBody Jugador jugador);

    @Operation(summary = "Actualizar datos de un jugador", description = "Actualiza la información de un jugador que ya existe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos actualizados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador);

    @Operation(summary = "Borrar un jugador", description = "Elimina a un jugador por completo de la base de datos usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar (ID no encontrado o dependencias)"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminar(@PathVariable Integer id);
}