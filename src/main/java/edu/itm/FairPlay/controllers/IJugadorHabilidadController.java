package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.JugadorHabilidad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Jugadores - Habilidades", description = "Manejo de la asignación de habilidades a los jugadores")
public interface IJugadorHabilidadController {

    @Operation(summary = "Ver todas las habilidades de jugadores", description = "Trae la lista de habilidades asignadas a los jugadores")
    ResponseEntity<?> listarHabilidadesDeJugadores();

    @Operation(summary = "Asignar habilidad a jugador", description = "Registra una habilidad para un jugador")
    ResponseEntity<?> asignarHabilidadAJugador(@RequestBody JugadorHabilidad jugadorHabilidad);

    @Operation(summary = "Eliminar habilidad de jugador", description = "Elimina una habilidad asignada a un jugador")
    ResponseEntity<?> eliminarHabilidadDeJugador(@PathVariable Integer id);
}