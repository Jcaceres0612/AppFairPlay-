package edu.itm.FairPlay.services;

import java.util.List;
import edu.itm.FairPlay.models.Equipo;



public interface IEquipoService {
    void registrarEquipo(Equipo equipo) throws Exception;
    List<Equipo> listarEquipos() throws Exception;
    void actualizarEquipo(Equipo equipo) throws Exception;
    void eliminarEquipo(Integer id) throws Exception;
}