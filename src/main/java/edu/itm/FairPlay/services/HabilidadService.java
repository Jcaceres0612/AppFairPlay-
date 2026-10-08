package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.repositories.HabilidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HabilidadService {

    private final HabilidadRepository repositorio;

    public HabilidadService(HabilidadRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarHabilidad(Habilidad habilidad) throws Exception {
        repositorio.registrarHabilidad(habilidad);
    }

    public List<Habilidad> listarHabilidades() throws Exception {
        return repositorio.listarHabilidades();
    }

    public void actualizarHabilidad(Habilidad habilidad) throws Exception {
        repositorio.actualizarHabilidad(habilidad);
    }

    public void eliminarHabilidad(Integer id) throws Exception {
        repositorio.eliminarHabilidad(id);
    }
}