package edu.itm.FairPlay.repositories;


import edu.itm.FairPlay.identities.Persona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonasRepository {

    public List<Persona> listaPersona() {

        List<Persona> result = new ArrayList<>();


        result.add(Persona.builder()
        .email("email1@mail.com")
                .cedula("11111")
                .direccion("callea")
                .nombre("Jose")
                .apellidos("Perea")
                .numero("301323")
                .build());

        result.add(Persona.builder()
                .email("email2@mail.com")
                .cedula("111112222")
                .direccion("calleab")
                .nombre("Josejose")
                .apellidos("Pereacaceres")
                .numero("301323343")
                .build());

        return result;


    }
}
