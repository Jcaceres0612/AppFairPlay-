package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Posicion;
import java.util.List;

public interface IPosicionService {
    List<Posicion> listarPosiciones() throws Exception;
    void registrarPosicion(Posicion posicion) throws Exception;
    void actualizarPosicion(Posicion posicion) throws Exception;
    void eliminarPosicion(Integer id) throws Exception;
}