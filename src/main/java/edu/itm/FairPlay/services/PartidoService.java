package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Partido;
import edu.itm.FairPlay.repositories.PartidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartidoService {

    private final PartidoRepository repositorio;

    public PartidoService(PartidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarPartido(Partido partido) throws Exception {
        repositorio.registrarPartido(partido);
    }

    public List<Partido> listarPartidos() throws Exception {
        return repositorio.listarPartidos();
    }

    public Partido buscarPorId(Integer id) throws Exception {
        Partido partido = repositorio.buscarPorId(id);
        if (partido == null) {
            throw new RecursoNoEncontradoException("El partido con ID " + id + " no fue encontrado en FairPlay.");
        }
        return partido;
    }

    public void actualizarPartido(Partido partido) throws Exception {
        repositorio.actualizarPartido(partido);
    }

    public void eliminarPartido(Integer id) throws Exception {
        repositorio.eliminarPartido(id);
    }
}