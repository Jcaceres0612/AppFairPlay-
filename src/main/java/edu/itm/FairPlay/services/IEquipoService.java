package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Equipo;
import java.util.List;


public interface IEquipoService {
    void registrarEquipo(Equipo equipo) throws Exception;
    List<Equipo> listarEquipos() throws Exception;
    void actualizarEquipo(Equipo equipo) throws Exception;
    void eliminarEquipo(Integer id) throws Exception;
}