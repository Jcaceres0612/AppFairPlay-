package edu.itm.FairPlay.services;

import edu.itm.FairPlay.identities.Persona;
import edu.itm.FairPlay.repositories.PersonasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class PersonaServices {
    private final PersonasRepository repository;

    public PersonaServices(PersonasRepository repository) {
        this.repository = repository;
    }

    public List<Persona> listaPersonas() {
        return repository.listaPersona();

    }
}