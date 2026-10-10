package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Partido;
import java.util.List;

public interface PartidoRepository {
    void registrarPartido(Partido partido) throws Exception;
    List<Partido> listarPartidos() throws Exception;
    void actualizarPartido(Partido partido) throws Exception;
    void eliminarPartido(Integer id) throws Exception;
}