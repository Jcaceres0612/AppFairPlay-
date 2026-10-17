package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Habilidad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Habilidades", description = "Gestión de las habilidades de los jugadores (Velocidad, Tiro, etc.)")
public interface IHabilidadController {

    @Operation(summary = "Ver todas las habilidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerTodasLasHabilidades(); // Fíjate que ya NO tiene "throws Exception"

    @Operation(summary = "Crear habilidad", description = "Regla: El nombre es obligatorio y debe tener al menos 3 letras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habilidad creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> guardar(@RequestBody Habilidad habilidad);

    @Operation(summary = "Actualizar habilidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidad actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno al actualizar")
    })
    ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Habilidad habilidad);

    @Operation(summary = "Borrar habilidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidad eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> eliminar(@PathVariable Integer id);
}