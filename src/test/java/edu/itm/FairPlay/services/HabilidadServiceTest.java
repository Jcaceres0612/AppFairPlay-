package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.repositories.HabilidadRepository;
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
class HabilidadServiceTest {

    @Mock
    private HabilidadRepository habilidadRepository;

    @InjectMocks
    private HabilidadService habilidadService;

    private Habilidad habilidadPrueba;

    @BeforeEach
    void setUp() {
        habilidadPrueba = new Habilidad();
        habilidadPrueba.setIdHabilidad(1);
        habilidadPrueba.setNombreHabilidad("Velocidad");
        habilidadPrueba.setDescripcion("Capacidad de desplazamiento rápido en el campo");
    }

    @Test
    void testListarHabilidades() throws Exception {
        when(habilidadRepository.listarHabilidades()).thenReturn(Collections.singletonList(habilidadPrueba));

        List<Habilidad> resultado = habilidadService.listarHabilidades();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Velocidad", resultado.get(0).getNombreHabilidad());
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(habilidadRepository.buscarPorId(1)).thenReturn(habilidadPrueba);

        Habilidad resultado = habilidadService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdHabilidad());
        assertEquals("Velocidad", resultado.getNombreHabilidad());
    }

    @Test
    void testBuscarPorIdNoExistente_LanzaExcepcion() throws Exception {
        when(habilidadRepository.buscarPorId(99)).thenReturn(null);

        assertThrows(RecursoNoEncontradoException.class, () -> {
            habilidadService.buscarPorId(99);
        });
    }

    @Test
    void testRegistrarHabilidad() throws Exception {
        doNothing().when(habilidadRepository).registrarHabilidad(any(Habilidad.class));

        assertDoesNotThrow(() -> habilidadService.registrarHabilidad(habilidadPrueba));
        verify(habilidadRepository, times(1)).registrarHabilidad(habilidadPrueba);
    }

    @Test
    void testActualizarHabilidad() throws Exception {
        doNothing().when(habilidadRepository).actualizarHabilidad(any(Habilidad.class));

        assertDoesNotThrow(() -> habilidadService.actualizarHabilidad(habilidadPrueba));
        verify(habilidadRepository, times(1)).actualizarHabilidad(habilidadPrueba);
    }

    @Test
    void testEliminarHabilidad() throws Exception {
        doNothing().when(habilidadRepository).eliminarHabilidad(1);

        assertDoesNotThrow(() -> habilidadService.eliminarHabilidad(1));
        verify(habilidadRepository, times(1)).eliminarHabilidad(1);
    }
}