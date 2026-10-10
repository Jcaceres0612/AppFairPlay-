package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Jugador;

import java.util.List;
import java.util.Optional;

public interface IJugadorService {

    Jugador guardar(Jugador jugador);

    List<Jugador> listarTodos();

    Optional<Jugador> buscarPorId (Integer id);

    void eliminar (Integer ID);
}
