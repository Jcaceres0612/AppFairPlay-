package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.models.Partido;
import edu.itm.FairPlay.repositories.EquipoRepository;
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
class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    private Equipo equipoPrueba;

    @BeforeEach
    void setUp() {
        equipoPrueba = new Equipo();
        equipoPrueba.setIdEquipo(1);
        equipoPrueba.setNombreEquipo("Deportivo ITM");

        // Configurando el objeto Partido relacionado que exige el modelo
        Partido partido = new Partido();
        partido.setIdPartido(1);
        equipoPrueba.setPartido(partido);
    }

    @Test
    void testListarEquipos() throws Exception {
        when(equipoRepository.listarEquipos()).thenReturn(Collections.singletonList(equipoPrueba));

        List<Equipo> resultado = equipoService.listarEquipos();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Deportivo ITM", resultado.get(0).getNombreEquipo());
    }

    @Test
    void testBuscarEquipoPorId() throws Exception {
        when(equipoRepository.buscarPorId(1)).thenReturn(equipoPrueba);

        Equipo resultado = equipoService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdEquipo());
        assertEquals("Deportivo ITM", resultado.getNombreEquipo());
    }

    @Test
    void testRegistrarEquipo() throws Exception {
        doNothing().when(equipoRepository).registrarEquipo(any(Equipo.class));

        assertDoesNotThrow(() -> equipoService.registrarEquipo(equipoPrueba));
        verify(equipoRepository, times(1)).registrarEquipo(equipoPrueba);
    }

    @Test
    void testEliminarEquipo() throws Exception {
        doNothing().when(equipoRepository).eliminarEquipo(1);

        assertDoesNotThrow(() -> equipoService.eliminarEquipo(1));
        verify(equipoRepository, times(1)).eliminarEquipo(1);
    }
}