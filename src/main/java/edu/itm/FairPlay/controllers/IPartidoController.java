package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Partido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Partidos", description = "Manejo de los partidos del torneo")
public interface IPartidoController {

    @Operation(summary = "Ver todos", description = "Trae la lista de partidos programados o jugados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    ResponseEntity<?> listarPartidos();

    @Operation(summary = "Registrar partido", description = "Guarda un partido nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Partido programado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    ResponseEntity<?> registrarPartido(@RequestBody Partido partido);

    @Operation(summary = "Actualizar partido", description = "Modifica la información o resultado de un partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partido actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> actualizarPartido(@PathVariable Integer id, @RequestBody Partido partido);

    @Operation(summary = "Borrar partido", description = "Elimina un partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partido eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminarPartido(@PathVariable Integer id);
}