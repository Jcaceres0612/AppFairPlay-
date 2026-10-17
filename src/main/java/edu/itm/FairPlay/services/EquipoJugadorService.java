package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.EquipoJugador;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoJugadorService implements IEquipoJugadorService {

    @Override
    public void registrarEquipoJugador(EquipoJugador equipoJugador) {
        // Tu lógica actual de guardado
    }

    @Override
    public List<EquipoJugador> listarPorEquipo(Integer idEquipo) {
        // Tu lógica de consulta
        return null;
    }

    @Override
    public void eliminarEquipoJugador(Integer idEquipo, Integer idJugador) {
        // Tu lógica para eliminar
    }
}