package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Jugador;
import java.util.List;

public interface JugadorRepository {

    void registrarJugador(Jugador jugador) throws Exception;

    List<Jugador> listarJugadores() throws Exception;

    void actualizarJugador(Jugador jugador) throws Exception;

    void eliminarJugador(Integer id) throws Exception;

}