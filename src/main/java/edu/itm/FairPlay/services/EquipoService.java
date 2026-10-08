package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.repositories.EquipoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository repositorio;

    public EquipoService(EquipoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarEquipo(Equipo equipo) throws Exception {
        repositorio.registrarEquipo(equipo);
    }

    public List<Equipo> listarEquipos() throws Exception {
        return repositorio.listarEquipos();
    }

    public void actualizarEquipo(Equipo equipo) throws Exception {
        repositorio.actualizarEquipo(equipo);
    }

    public void eliminarEquipo(Integer id) throws Exception {
        repositorio.eliminarEquipo(id);
    }
}