package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Habilidad;
import java.util.List;

public interface IHabilidadService {

    // Ahora devuelve la Habilidad creada (con su nuevo ID)
    Habilidad registrarHabilidad(Habilidad habilidad) throws Exception;

    List<Habilidad> listarHabilidades() throws Exception;

    // Añadimos el método buscarPorId que se crea en el servicio
    Habilidad buscarPorId(Integer id) throws Exception;

    // Ahora recibe el ID de la URL y los datos nuevos a actualizar, devolviendo el resultado
    Habilidad actualizarHabilidad(Integer id, Habilidad habilidad) throws Exception;

    void eliminarHabilidad(Integer id) throws Exception;
}