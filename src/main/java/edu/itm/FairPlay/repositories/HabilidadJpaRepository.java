package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadJpaRepository extends JpaRepository<Habilidad, Integer> {
    // ¡Sí, está vacío! No necesitas escribir nada más aquí adentro.
}