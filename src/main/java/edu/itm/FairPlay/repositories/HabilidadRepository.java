package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Habilidad;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface HabilidadRepository {

    void registrarHabilidad(Habilidad habilidad) throws Exception;
    List<Habilidad> listarHabilidades() throws Exception;
    void actualizarHabilidad(Habilidad habilidad) throws Exception;
    void eliminarHabilidad(Integer id) throws Exception;
}