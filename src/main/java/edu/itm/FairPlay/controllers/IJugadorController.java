package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Jugador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Jugadores", description = "Todo lo que tiene que ver con los jugadores del torneo")
public interface IJugadorController {

    @Operation(summary = "Ver todos los jugadores", description = "Trae la lista completa de los jugadores registrados en la base de datos.")
    ResponseEntity<?> listarTodos() throws Exception;

    @Operation(summary = "Registrar un jugador nuevo", description = "Ojo: No le mandes el idJugador en el JSON para que SQL Server le asigne el número automáticamente.")
    ResponseEntity<?> guardar(@RequestBody Jugador jugador) throws Exception;

    @Operation(summary = "Actualizar datos de un jugador", description = "Actualiza la información de un jugador que ya existe.")
    ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador) throws Exception;

    @Operation(summary = "Borrar un jugador", description = "Elimina a un jugador por completo de la base de datos usando su ID.")
    ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception;
}