package edu.itm.FairPlay.services;

import edu.itm.FairPlay.exceptions.RecursoNoEncontradoException;
import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.repositories.PosicionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionService implements IPosicionService {

    private final PosicionRepository posicionRepository;

    public PosicionService(PosicionRepository posicionRepository) {
        this.posicionRepository = posicionRepository;
    }

    @Override
    public void registrarPosicion(Posicion posicion) throws Exception {
        posicionRepository.registrarPosicion(posicion);
    }

    @Override
    public List<Posicion> listarPosiciones() throws Exception {
        return posicionRepository.listarPosiciones();
    }

    @Override
    public Posicion buscarPorId(Integer id) throws Exception {
        Posicion posicion = posicionRepository.buscarPorId(id);
        if (posicion == null) {
            throw new RecursoNoEncontradoException("La posición con ID " + id + " no fue encontrada en FairPlay.");
        }
        return posicion;
    }

    @Override
    public void actualizarPosicion(Posicion posicion) throws Exception {
        posicionRepository.actualizarPosicion(posicion);
    }

    @Override
    public void eliminarPosicion(Integer id) throws Exception {
        posicionRepository.eliminarPosicion(id);
    }
}