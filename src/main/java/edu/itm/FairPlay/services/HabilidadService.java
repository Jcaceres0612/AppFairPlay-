package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.repositories.HabilidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HabilidadService implements IHabilidadService { // <-- Lo conectamos a la interfaz

    private final HabilidadRepository repositorio;

    public HabilidadService(HabilidadRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void registrarHabilidad(Habilidad habilidad) throws Exception {

        if (habilidad.getNombreHabilidad() == null || habilidad.getNombreHabilidad().trim().isEmpty()) {
            throw new Exception("El nombre de la habilidad es obligatorio.");
        }
        if (habilidad.getNombreHabilidad().length() < 3) {
            throw new Exception(" El nombre debe tener al menos 3 caracteres.");
        }


        repositorio.registrarHabilidad(habilidad);
    }

    @Override
    public List<Habilidad> listarHabilidades() throws Exception {
        return repositorio.listarHabilidades();
    }

    @Override
    public void actualizarHabilidad(Habilidad habilidad) throws Exception {
         if (habilidad.getNombreHabilidad() == null || habilidad.getNombreHabilidad().trim().isEmpty()) {
            throw new Exception("El nombre de la habilidad no puede quedar vacío.");
        }

        repositorio.actualizarHabilidad(habilidad);
    }

    @Override
    public void eliminarHabilidad(Integer id) throws Exception {
        repositorio.eliminarHabilidad(id);
    }
}