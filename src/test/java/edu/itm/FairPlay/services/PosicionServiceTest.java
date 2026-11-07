package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.repositories.PosicionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PosicionServiceTest {

    @Mock
    private PosicionRepository posicionRepository;

    @InjectMocks
    private PosicionService posicionService;

    private Posicion posicionPrueba;

    @BeforeEach
    void setUp() {
        posicionPrueba = new Posicion();
        posicionPrueba.setIdPosicion(1);
        posicionPrueba.setNombrePosicion("Delantero");
        posicionPrueba.setDescripcion("Jugador encargado del ataque y la definición");
    }

    @Test
    void testListarPosiciones() throws Exception {
        when(posicionRepository.listarPosiciones()).thenReturn(Collections.singletonList(posicionPrueba));

        List<Posicion> resultado = posicionService.listarPosiciones();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Delantero", resultado.get(0).getNombrePosicion());
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(posicionRepository.buscarPorId(1)).thenReturn(posicionPrueba);

        Posicion resultado = posicionService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPosicion());
        assertEquals("Delantero", resultado.getNombrePosicion());
    }

    @Test
    void testBuscarPorIdNoExistente_LanzaExcepcion() throws Exception {
        when(posicionRepository.buscarPorId(99)).thenReturn(null);

        assertThrows(RecursoNoEncontradoException.class, () -> {
            posicionService.buscarPorId(99);
        });
    }

    @Test
    void testRegistrarPosicion() throws Exception {
        doNothing().when(posicionRepository).registrarPosicion(any(Posicion.class));

        assertDoesNotThrow(() -> posicionService.registrarPosicion(posicionPrueba));
        verify(posicionRepository, times(1)).registrarPosicion(posicionPrueba);
    }

    @Test
    void testActualizarPosicion() throws Exception {
        doNothing().when(posicionRepository).actualizarPosicion(any(Posicion.class));

        assertDoesNotThrow(() -> posicionService.actualizarPosicion(posicionPrueba));
        verify(posicionRepository, times(1)).actualizarPosicion(posicionPrueba);
    }

    @Test
    void testEliminarPosicion() throws Exception {
        doNothing().when(posicionRepository).eliminarPosicion(1);

        assertDoesNotThrow(() -> posicionService.eliminarPosicion(1));
        verify(posicionRepository, times(1)).eliminarPosicion(1);
    }
}