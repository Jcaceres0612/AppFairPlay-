package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Partido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Partidos", description = "Manejo de los partidos del torneo")
public interface IPartidoController {

    @Operation(summary = "Ver todos", description = "Trae la lista de partidos programados o jugados")
    ResponseEntity<?> listarPartidos();

    @Operation(summary = "Registrar partido", description = "Guarda un partido nuevo")
    ResponseEntity<?> registrarPartido(@RequestBody Partido partido);

    @Operation(summary = "Actualizar partido", description = "Modifica la información o resultado de un partido")
    ResponseEntity<?> actualizarPartido(@PathVariable Integer id, @RequestBody Partido partido);

    @Operation(summary = "Borrar partido", description = "Elimina un partido")
    ResponseEntity<?> eliminarPartido(@PathVariable Integer id);
}