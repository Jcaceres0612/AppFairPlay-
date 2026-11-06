package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.repositories.JugadorJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JugadorServiceTest {

    @Mock
    private JugadorJpaRepository jugadorRepository;

    @InjectMocks
    private JugadorService jugadorService;

    private Jugador jugadorPrueba;

    @BeforeEach
    void setUp() {
        jugadorPrueba = new Jugador();
        jugadorPrueba.setIdjugador(1);
        jugadorPrueba.setNombre("James");
        jugadorPrueba.setApellido("Rodriguez");
        jugadorPrueba.setFechaRegistro(LocalDate.now());

        Posicion posicion = new Posicion();
        posicion.setIdPosicion(4);
        jugadorPrueba.setPosicion(posicion);
    }

    @Test
    void testListarJugadores() {
        when(jugadorRepository.findAll()).thenReturn(Collections.singletonList(jugadorPrueba));

        List<Jugador> resultado = jugadorService.listarJugadores();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("James", resultado.get(0).getNombre());
    }

    @Test
    void testBuscarPorId_Exitoso() {
        when(jugadorRepository.findById(1)).thenReturn(Optional.of(jugadorPrueba));

        Jugador resultado = jugadorService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Rodriguez", resultado.getApellido());
    }

    @Test
    void testBuscarPorId_NoEncontrado_DeberiaLanzarExcepcion() {
        when(jugadorRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> {
            jugadorService.buscarPorId(99);
        });
    }

    @Test
    void testRegistrarJugador() {
        when(jugadorRepository.save(any(Jugador.class))).thenReturn(jugadorPrueba);

        Jugador resultado = jugadorService.registrarJugador(jugadorPrueba);

        assertNotNull(resultado);
        assertEquals("James", resultado.getNombre());
        verify(jugadorRepository, times(1)).save(jugadorPrueba);
    }

    @Test
    void testActualizarJugador() {
        Jugador jugadorActualizadoDatos = new Jugador();
        jugadorActualizadoDatos.setNombre("James David");
        jugadorActualizadoDatos.setApellido("Rodriguez Rubio");
        jugadorActualizadoDatos.setFechaRegistro(LocalDate.now());

        Posicion posicion = new Posicion();
        posicion.setIdPosicion(4);
        jugadorActualizadoDatos.setPosicion(posicion);

        when(jugadorRepository.findById(1)).thenReturn(Optional.of(jugadorPrueba));
        when(jugadorRepository.save(any(Jugador.class))).thenReturn(jugadorPrueba);

        Jugador resultado = jugadorService.actualizarJugador(1, jugadorActualizadoDatos);

        assertNotNull(resultado);
        assertEquals("James David", resultado.getNombre());
        verify(jugadorRepository, times(1)).save(jugadorPrueba);
    }

    @Test
    void testEliminarJugador() {
        when(jugadorRepository.findById(1)).thenReturn(Optional.of(jugadorPrueba));
        doNothing().when(jugadorRepository).delete(jugadorPrueba);

        assertDoesNotThrow(() -> jugadorService.eliminarJugador(1));
        verify(jugadorRepository, times(1)).delete(jugadorPrueba);
    }
}