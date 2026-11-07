package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.repositories.HabilidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService implements IHabilidadService {

    private final HabilidadRepository habilidadRepository;

    public HabilidadService(HabilidadRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    @Override
    public void registrarHabilidad(Habilidad habilidad) throws Exception {
        habilidadRepository.registrarHabilidad(habilidad);
    }

    @Override
    public List<Habilidad> listarHabilidades() throws Exception {
        return habilidadRepository.listarHabilidades();
    }

    @Override
    public Habilidad buscarPorId(Integer id) throws Exception {
        Habilidad habilidad = habilidadRepository.buscarPorId(id);
        if (habilidad == null) {
            throw new RecursoNoEncontradoException("La habilidad con ID " + id + " no fue encontrada en FairPlay.");
        }
        return habilidad;
    }

    @Override
    public void actualizarHabilidad(Habilidad habilidad) throws Exception {
        habilidadRepository.actualizarHabilidad(habilidad);
    }

    @Override
    public void eliminarHabilidad(Integer id) throws Exception {
        habilidadRepository.eliminarHabilidad(id);
    }
}