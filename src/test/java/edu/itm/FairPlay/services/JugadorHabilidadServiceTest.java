package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.JugadorHabilidad;
import edu.itm.FairPlay.repositories.JugadorHabilidadRepository;
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
class JugadorHabilidadServiceTest {

    @Mock
    private JugadorHabilidadRepository jugadorHabilidadRepository;

    @InjectMocks
    private JugadorHabilidadService jugadorHabilidadService;

    private JugadorHabilidad jhPrueba;

    @BeforeEach
    void setUp() {
        jhPrueba = new JugadorHabilidad();
        jhPrueba.setIdJugadorHabilidad(1);
        jhPrueba.setIdJugador(10);
        jhPrueba.setIdHabilidad(2);
        jhPrueba.setValor(85);
    }

    @Test
    void testListarJugadorHabilidades() throws Exception {
        when(jugadorHabilidadRepository.listarJugadorHabilidades()).thenReturn(Collections.singletonList(jhPrueba));

        List<JugadorHabilidad> resultado = jugadorHabilidadService.listarJugadorHabilidades();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(85, resultado.get(0).getValor());
        verify(jugadorHabilidadRepository, times(1)).listarJugadorHabilidades();
    }

    @Test
    void testBuscarPorId_Exitoso() throws Exception {
        when(jugadorHabilidadRepository.buscarPorId(1)).thenReturn(jhPrueba);

        JugadorHabilidad resultado = jugadorHabilidadService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdJugadorHabilidad());
        assertEquals(85, resultado.getValor());
    }

    @Test
    void testBuscarPorId_NoExistente_LanzaExcepcion() throws Exception {
        when(jugadorHabilidadRepository.buscarPorId(99)).thenReturn(null);

        assertThrows(RecursoNoEncontradoException.class, () -> {
            jugadorHabilidadService.buscarPorId(99);
        });
    }

    @Test
    void testRegistrarJugadorHabilidad_Exitoso() throws Exception {
        doNothing().when(jugadorHabilidadRepository).registrarJugadorHabilidad(any(JugadorHabilidad.class));

        assertDoesNotThrow(() -> jugadorHabilidadService.registrarJugadorHabilidad(jhPrueba));
        verify(jugadorHabilidadRepository, times(1)).registrarJugadorHabilidad(jhPrueba);
    }

    @Test
    void testRegistrarJugadorHabilidad_ValorInvalido_LanzaExcepcion() {
        JugadorHabilidad jhInvalido = new JugadorHabilidad();
        jhInvalido.setIdJugador(10);
        jhInvalido.setIdHabilidad(2);
        jhInvalido.setValor(-5); // Valor negativo no permitido

        assertThrows(IllegalArgumentException.class, () -> {
            jugadorHabilidadService.registrarJugadorHabilidad(jhInvalido);
        });
    }

    @Test
    void testActualizarJugadorHabilidad() throws Exception {
        doNothing().when(jugadorHabilidadRepository).actualizarJugadorHabilidad(any(JugadorHabilidad.class));

        assertDoesNotThrow(() -> jugadorHabilidadService.actualizarJugadorHabilidad(jhPrueba));
        verify(jugadorHabilidadRepository, times(1)).actualizarJugadorHabilidad(jhPrueba);
    }

    @Test
    void testEliminarJugadorHabilidad() throws Exception {
        doNothing().when(jugadorHabilidadRepository).eliminarJugadorHabilidad(1);

        assertDoesNotThrow(() -> jugadorHabilidadService.eliminarJugadorHabilidad(1));
        verify(jugadorHabilidadRepository, times(1)).eliminarJugadorHabilidad(1);
    }
}