package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Posicion;
import java.util.List;

public interface PosicionRepository {
    void registrarPosicion(Posicion posicion) throws Exception;
    List<Posicion> listarPosiciones() throws Exception;
    void actualizarPosicion(Posicion posicion) throws Exception;
    void eliminarPosicion(Integer id) throws Exception;
}