package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Partido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Partidos", description = "Gestión de encuentros y partidos del torneo")
public interface IPartidoController {

    @Operation(summary = "Ver partidos", description = "Lista todos los partidos programados")
    ResponseEntity<?> listarPartidos();

    @Operation(summary = "Registrar partido", description = "Crea un nuevo partido en el sistema")
    ResponseEntity<?> registrarPartido(@RequestBody Partido partido);

    @Operation(summary = "Actualizar partido", description = "Modifica los datos de un partido existente")
    ResponseEntity<?> actualizarPartido(@PathVariable Integer id, @RequestBody Partido partido);

    @Operation(summary = "Eliminar partido", description = "Borra un partido por su ID")
    ResponseEntity<?> eliminarPartido(@PathVariable Integer id);
}