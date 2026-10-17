package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Partido;
import java.util.List;

public interface IPartidoService {
    void registrarPartido(Partido partido) throws Exception;
    List<Partido> listarPartidos() throws Exception;
    void actualizarPartido(Partido partido) throws Exception;
    void eliminarPartido(Integer id) throws Exception;
}