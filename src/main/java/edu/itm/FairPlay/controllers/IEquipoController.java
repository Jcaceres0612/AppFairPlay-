package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Equipo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

// ¡Toda la documentación se queda en este archivo!
@Tag(name = "Equipos", description = "Manejo de los equipos inscritos en el torneo")
public interface IEquipoController {

    @Operation(summary = "Ver todos", description = "Trae la lista de equipos")
    ResponseEntity<?> listarEquipos();

    @Operation(summary = "Registrar equipo", description = "Guarda un equipo nuevo")
    ResponseEntity<?> registrarEquipo(@RequestBody Equipo equipo);

    @Operation(summary = "Actualizar equipo", description = "Modifica un equipo")
    ResponseEntity<?> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipo equipo);

    @Operation(summary = "Borrar equipo", description = "Elimina un equipo")
    ResponseEntity<?> eliminarEquipo(@PathVariable Integer id);
}