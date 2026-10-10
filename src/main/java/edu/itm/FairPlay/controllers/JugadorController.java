package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.services.IJugadorService;
// ¡Asegúrate de agregar estos dos imports nuevos!
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugadores")
// Esta etiqueta le da el título a toda la sección en Swagger
@Tag(name = "Jugadores", description = "Todo lo que tiene que ver con los jugadores del torneo")
public class JugadorController {

    @Autowired
    private IJugadorService jugadorService;

    @GetMapping
    @Operation(summary = "Ver todos los jugadores", description = "Trae la lista completa de los jugadores registrados en la base de datos.")
    public ResponseEntity<?> listarTodos() {
        // Tu código de listarTodos queda igualito...
        return ResponseEntity.ok(jugadorService.listarTodos());
    }

    @PostMapping
    @Operation(summary = "Registrar un jugador nuevo", description = "Ojo: No le mandes el idJugador en el JSON para que SQL Server le asigne el número automáticamente.")
    public ResponseEntity<?> guardar(@RequestBody Jugador jugador) {
        // Tu código de guardar queda igualito...
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("¡Listo! El jugador fue guardado correctamente en la base de datos de FairPlay.");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un jugador", description = "Actualiza la información de un jugador que ya existe. Mándale el ID exacto que quieres modificar.")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador) {
        // Tu código de actualizar queda igualito...
        jugador.setIdJugador(id);
        jugadorService.guardar(jugador);
        return ResponseEntity.ok("¡El jugador con ID " + id + " fue actualizado exitosamente en FairPlay!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un jugador", description = "Elimina a un jugador por completo de la base de datos usando su ID.")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        // Tu código de eliminar queda igualito...
        jugadorService.eliminar(id);
        return ResponseEntity.ok("El jugador con ID " + id + " ha sido eliminado correctamente.");
    }
}