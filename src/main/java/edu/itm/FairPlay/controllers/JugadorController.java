package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.services.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugadores", description = "CRUD de jugadores de FairPlay")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @Operation(summary = "Listar todos los jugadores")
    @ApiResponse(responseCode = "200", description = "Lista de jugadores")
    @GetMapping
    public ResponseEntity<List<Jugador>> listarTodos() {
        return new ResponseEntity<>(jugadorService.listarJugadores(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar un jugador por ID")
    @ApiResponse(responseCode = "200", description = "Jugador encontrado")
    @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(jugadorService.buscarPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Registrar un jugador nuevo")
    @ApiResponse(responseCode = "201", description = "Jugador creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public ResponseEntity<Jugador> registrarJugador(@Valid @RequestBody Jugador nuevoJugador) {
        return new ResponseEntity<>(jugadorService.registrarJugador(nuevoJugador), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un jugador existente")
    @ApiResponse(responseCode = "200", description = "Jugador actualizado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Integer id,
                                                     @Valid @RequestBody Jugador jugadorActualizado) {
        return new ResponseEntity<>(jugadorService.actualizarJugador(id, jugadorActualizado), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un jugador")
    @ApiResponse(responseCode = "200", description = "Jugador eliminado")
    @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarJugador(@PathVariable Integer id) {
        jugadorService.eliminarJugador(id);
        return new ResponseEntity<>("Jugador eliminado correctamente.", HttpStatus.OK);
    }
}