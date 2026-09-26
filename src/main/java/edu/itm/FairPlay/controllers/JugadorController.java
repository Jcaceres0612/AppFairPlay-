package edu.itm.FairPlay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.repositories.JugadorRepository;
import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorRepository repositorio;

    public JugadorController(JugadorRepository repositorio) {
        this.repositorio = repositorio;
    }

/*este metodo nos sirve para crear los jugadores y llevarlos a la base de datos*/
    @PostMapping
    public ResponseEntity<String> crearJugador(@RequestBody Jugador jugador) {
        try {
            repositorio.registrarJugador(jugador);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Listo! El jugador fue guardado correctamente en la base de datos de FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al guardar: " + e.getMessage());
        }
    }

/*este metodo nos sirve para saber que cuantos usuarios hay guardados en la base de datos creada*/
    @GetMapping // <-- Agregado para habilitar la consulta HTTP
    public List<Jugador> obtenerTodosLosJugadores() {
        try {
            return repositorio.listarJugadores();
        } catch (Exception e) {
            return null;
        }
    }

  /*este metodo nos ayuda a actualizar la informacion que sea necesaria de un jugador almacenador*/
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarJugador(@PathVariable Integer id, @RequestBody Jugador jugador) {
        try {
            // Asignamos el ID que viene en la URL al objeto
            jugador.setIdJugador(id);

            // Llamamos al repositorio que creamos
            repositorio.actualizarJugador(jugador);

            return ResponseEntity.ok("¡El jugador con ID " + id + " fue actualizado exitosamente en FairPlay!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }
}

