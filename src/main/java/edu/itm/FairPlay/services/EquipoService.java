package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.repositories.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public void registrarEquipo(Equipo equipo) throws Exception {
        equipoRepository.registrarEquipo(equipo);
    }

    public List<Equipo> listarEquipos() throws Exception {
        return equipoRepository.listarEquipos();
    }

    public Equipo buscarPorId(Integer id) throws Exception {
        return equipoRepository.buscarPorId(id);
    }

    public void actualizarEquipo(Equipo equipo) throws Exception {
        equipoRepository.actualizarEquipo(equipo);
    }

    public void eliminarEquipo(Integer id) throws Exception {
        equipoRepository.eliminarEquipo(id);
    }
}