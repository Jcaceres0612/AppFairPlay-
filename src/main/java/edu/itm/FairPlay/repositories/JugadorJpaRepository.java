package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorJpaRepository extends JpaRepository<Jugador, Integer> {
 }