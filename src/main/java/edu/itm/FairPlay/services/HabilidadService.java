

package edu.itm.FairPlay.services;

import edu.itm.FairPlay.models.Habilidad;
import edu.itm.FairPlay.repositories.HabilidadJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadService {

    private final HabilidadJpaRepository habilidadRepository;

    // Inyección de dependencias del repositorio JPA
    public HabilidadService(HabilidadJpaRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    public Habilidad registrarHabilidad(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    public List<Habilidad> listarHabilidades() {
        return habilidadRepository.findAll();
    }


    public Habilidad buscarPorId(Integer id) throws Exception {
        Optional<Habilidad> habilidadEncontrada = habilidadRepository.findById(id);

        if (habilidadEncontrada.isPresent()) {
            return habilidadEncontrada.get();
        } else {
            throw new Exception("Habilidad con ID " + id + " no encontrada.");
        }
    }


    public Habilidad actualizarHabilidad(Integer id, Habilidad habilidadActualizada) throws Exception {

        Habilidad habilidadExistente = buscarPorId(id);

        // Modificamos en memoria (Nota: asegúrate de usar el nombre exacto de tu variable)
        habilidadExistente.setNombreHabilidad(habilidadActualizada.getNombreHabilidad());

        // Guardamos el cambio
        return habilidadRepository.save(habilidadExistente);
    }

    public void eliminarHabilidad(Integer id) throws Exception {
        Habilidad habilidadExistente = buscarPorId(id);
        habilidadRepository.delete(habilidadExistente);
    }
}