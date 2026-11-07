package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.models.EquipoJugador;
import edu.itm.FairPlay.repositories.EquipoJugadorRepository;
import edu.itm.FairPlay.repositories.EquipoRepository;
import edu.itm.FairPlay.repositories.JugadorJpaRepository;
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
class EquipoJugadorServiceTest {

    @Mock
    private EquipoJugadorRepository equipoJugadorRepo;

    @Mock
    private EquipoRepository equipoRepo;

    @Mock
    private JugadorJpaRepository jugadorRepo;

    @InjectMocks
    private EquipoJugadorService equipoJugadorService;

    private EquipoJugador ejPrueba;

    @BeforeEach
    void setUp() {
        ejPrueba = new EquipoJugador();
        ejPrueba.setIdEquipoJugador(1);
        ejPrueba.setIdEquipo(10);
        ejPrueba.setIdJugador(100);
    }

    @Test
    void testAsociarJugadorAEquipo_Exitoso() throws Exception {
        when(jugadorRepo.existsById(100)).thenReturn(true);
        when(equipoRepo.buscarPorId(10)).thenReturn(new Equipo());
        doNothing().when(equipoJugadorRepo).registrarEquipoJugador(any(EquipoJugador.class));

        assertDoesNotThrow(() -> equipoJugadorService.asociarJugadorAEquipo(ejPrueba));
        verify(equipoJugadorRepo, times(1)).registrarEquipoJugador(ejPrueba);
    }

    @Test
    void testAsociarJugadorAEquipo_IdsNulos_LanzaExcepcion() {
        EquipoJugador ejInvalido = new EquipoJugador();
        ejInvalido.setIdJugador(null);
        ejInvalido.setIdEquipo(null);

        assertThrows(IllegalArgumentException.class, () -> {
            equipoJugadorService.asociarJugadorAEquipo(ejInvalido);
        });
    }

    @Test
    void testAsociarJugadorAEquipo_JugadorNoExiste_LanzaExcepcion() {
        when(jugadorRepo.existsById(100)).thenReturn(false);

        assertThrows(RecursoNoEncontradoException.class, () -> {
            equipoJugadorService.asociarJugadorAEquipo(ejPrueba);
        });
    }

    @Test
    void testAsociarJugadorAEquipo_EquipoNoExiste_LanzaExcepcion() throws Exception {
        when(jugadorRepo.existsById(100)).thenReturn(true);
        when(equipoRepo.buscarPorId(10)).thenReturn(null);

        assertThrows(RecursoNoEncontradoException.class, () -> {
            equipoJugadorService.asociarJugadorAEquipo(ejPrueba);
        });
    }

    @Test
    void testListarEquiposJugadores() throws Exception {
        when(equipoJugadorRepo.listarEquipoJugadores()).thenReturn(Collections.singletonList(ejPrueba));

        List<EquipoJugador> resultado = equipoJugadorService.listarEquiposJugadores();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(10, resultado.get(0).getIdEquipo());
    }
}