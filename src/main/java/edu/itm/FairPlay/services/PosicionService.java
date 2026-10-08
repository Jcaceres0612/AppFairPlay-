package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Posicion;
import edu.itm.FairPlay.repositories.PosicionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionService { // Ojo,

    private final PosicionRepository posicionRepository;

    public PosicionService(PosicionRepository posicionRepository) {
        this.posicionRepository = posicionRepository;
    }

    public void registrarPosicion(Posicion posicion) throws Exception {
        posicionRepository.registrarPosicion(posicion);
    }

    public List<Posicion> listarPosiciones() throws Exception {
        return posicionRepository.listarPosiciones();
    }

    public void actualizarPosicion(Posicion posicion) throws Exception {
        posicionRepository.actualizarPosicion(posicion);
    }

    public void eliminarPosicion(Integer id) throws Exception {
        posicionRepository.eliminarPosicion(id);
    }
}