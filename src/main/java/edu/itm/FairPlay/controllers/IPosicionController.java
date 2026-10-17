package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Posicion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Posiciones", description = "Configuración de las posiciones en la cancha (Arquero, Defensa, etc.)")
public interface IPosicionController {

    @Operation(summary = "Ver todas las posiciones", description = "Trae la lista de las posiciones que pueden jugar en la cancha.")
    ResponseEntity<?> listarTodos() throws Exception;

    @Operation(summary = "Crear una posición nueva", description = "Ojo: Aquí SÍ tienes que mandar el idPosicion en el JSON.")
    ResponseEntity<?> registrarPosicion(@RequestBody Posicion posicion) throws Exception;

    @Operation(summary = "Actualizar una posición", description = "Le cambias el nombre o la descripción a una posición que ya existe.")
    ResponseEntity<?> actualizarPosicion(@PathVariable Integer id, @RequestBody Posicion posicion) throws Exception;

    @Operation(summary = "Borrar una posición", description = "Pilas: Si hay jugadores usando esta posición, SQL Server no te va a dejar borrarla.")
    ResponseEntity<?> eliminarPosicion(@PathVariable Integer id) throws Exception;
}