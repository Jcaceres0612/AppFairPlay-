package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Habilidad;
import java.util.List;

public interface HabilidadRepository {
    void registrarHabilidad(Habilidad habilidad) throws Exception;
    List<Habilidad> listarHabilidades() throws Exception;
    void actualizarHabilidad(Habilidad habilidad) throws Exception;
    void eliminarHabilidad(Integer id) throws Exception;
}