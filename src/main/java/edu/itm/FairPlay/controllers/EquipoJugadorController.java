package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.services.IEquipoJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipo-jugadores")
public class EquipoJugadorController implements IEquipoJugadorController {

    @Autowired
    private IEquipoJugadorService equipoJugadorService;

    @Override
    @GetMapping
    public ResponseEntity<?> listarEquiposJugadores() {
        try {
            return ResponseEntity.ok(equipoJugadorService.listarEquiposJugadores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar las asociaciones equipo-jugador: " + e.getMessage());
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> asociarJugadorAEquipo(@RequestBody EquipoJugador equipoJugador) {
        try {
            equipoJugadorService.asociarJugadorAEquipo(equipoJugador);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Jugador asociado exitosamente al equipo en FairPlay!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> desasociarJugadorDeEquipo(@PathVariable Integer id) {
        try {
            equipoJugadorService.desasociarJugadorDeEquipo(id);
            return ResponseEntity.ok("Relación Equipo-Jugador eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al desasociar: " + e.getMessage());
        }
    }
}