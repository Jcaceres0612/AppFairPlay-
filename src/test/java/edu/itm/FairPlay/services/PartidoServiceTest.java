package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Partido;
import edu.itm.FairPlay.repositories.PartidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartidoServiceTest {

    @Mock
    private PartidoRepository partidoRepository;

    @InjectMocks
    private PartidoService partidoService;

    private Partido partidoPrueba;

    @BeforeEach
    void setUp() {
        partidoPrueba = new Partido();
        partidoPrueba.setIdPartido(1);
        partidoPrueba.setFechaHora(LocalDateTime.now());
        partidoPrueba.setTipoPartido("Torneo Local");
        partidoPrueba.setEstado("Programado");
    }

    @Test
    void testListarPartidos() throws Exception {
        when(partidoRepository.listarPartidos()).thenReturn(Collections.singletonList(partidoPrueba));

        List<Partido> resultado = partidoService.listarPartidos();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Torneo Local", resultado.get(0).getTipoPartido());
    }

    @Test
    void testBuscarPartidoPorId() throws Exception {
        when(partidoRepository.buscarPorId(1)).thenReturn(partidoPrueba);

        Partido resultado = partidoService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPartido());
    }

    @Test
    void testBuscarPartidoNoExistente_LanzaExcepcion() throws Exception {
        when(partidoRepository.buscarPorId(99)).thenReturn(null);

        assertThrows(RecursoNoEncontradoException.class, () -> {
            partidoService.buscarPorId(99);
        });
    }

    @Test
    void testRegistrarPartido() throws Exception {
        doNothing().when(partidoRepository).registrarPartido(any(Partido.class));

        assertDoesNotThrow(() -> partidoService.registrarPartido(partidoPrueba));
        verify(partidoRepository, times(1)).registrarPartido(partidoPrueba);
    }
}