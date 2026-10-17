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

  public class JugadorController implements IJugadorController {

    @Autowired
    private IJugadorService jugadorService;

    @GetMapping
     public ResponseEntity<?> listarTodos() {
        // Tu código de listarTodos queda igualito...
        return ResponseEntity.ok(jugadorService.listarTodos());
    }

    @PostMapping
     public ResponseEntity<?> guardar(@RequestBody Jugador jugador) {
        // Tu código de guardar queda igualito...
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("¡Listo! El jugador fue guardado correctamente en la base de datos de FairPlay.");
    }

    @PutMapping("/{id}")
     public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador) {
        // Tu código de actualizar queda igualito...
        jugador.setIdJugador(id);
        jugadorService.guardar(jugador);
        return ResponseEntity.ok("¡El jugador con ID " + id + " fue actualizado exitosamente en FairPlay!");
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        // Tu código de eliminar queda igualito...
        jugadorService.eliminar(id);
        return ResponseEntity.ok("El jugador con ID " + id + " ha sido eliminado correctamente.");
    }
}