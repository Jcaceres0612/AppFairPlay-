package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.EquipoJugador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Equipos - Jugadores", description = "Manejo de la asignación de jugadores a los equipos")
public interface IEquipoJugadorController {

    @Operation(summary = "Ver todas las asociaciones", description = "Trae la lista de jugadores asignados a equipos")
    ResponseEntity<?> listarEquiposJugadores();

    @Operation(summary = "Asociar jugador a equipo", description = "Asigna un jugador a un equipo")
    ResponseEntity<?> asociarJugadorAEquipo(@RequestBody EquipoJugador equipoJugador);

    @Operation(summary = "Desasociar jugador de equipo", description = "Elimina la asignación de un jugador a un equipo")
    ResponseEntity<?> desasociarJugadorDeEquipo(@PathVariable Integer id);
}