package edu.itm.FairPlay.controllers;

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

    // --------------------------------------------------------
    // ENDPOINT PARA CREAR UN JUGADOR (La 'C' de tu CRUL)
    // --------------------------------------------------------
    @PostMapping
    public ResponseEntity<String> crearJugador(@RequestBody Jugador jugador) {
        try {
            repositorio.registrarJugador(jugador);
            return ResponseEntity.ok("¡Listo! El jugador fue guardado correctamente en la base de datos de FairPlay.");
        } catch (Exception e) {
            return ResponseEntity.ok("Rayos, hubo un error al guardar: " + e.getMessage());
        }
    }

    // --------------------------------------------------------
    // ENDPOINT PARA LISTAR JUGADORES (La 'R/L' de tu CRUL)
    // --------------------------------------------------------
    @GetMapping
    public List<Jugador> obtenerTodosLosJugadores() {
        try {
            return repositorio.listarJugadores();
        } catch (Exception e) {
            return null;
        }
    }

    // --------------------------------------------------------
    // ENDPOINT PARA ACTUALIZAR UN JUGADOR (La 'U' de tu CRUL)
    // --------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarJugador(@PathVariable Integer id, @RequestBody Jugador jugador) {
        try {
            // Asignamos el ID que viene en la URL al objeto
            jugador.setIdJugador(id);

            // Llamamos al repositorio que creamos
            repositorio.actualizarJugador(jugador);

            return ResponseEntity.ok("¡El jugador con ID " + id + " fue actualizado exitosamente en FairPlay!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Rayos, hubo un error al actualizar: " + e.getMessage());
        }
    }
}