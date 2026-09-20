package edu.itm.FairPlay.controllers;


import edu.itm.FairPlay.identities.Persona;
import edu.itm.FairPlay.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Prueba {




    @Autowired
    private PersonaServices services;


    @GetMapping("/ping")
    public String prueba(){
        return "pong";


    }
    @GetMapping("/pruebalista")
    public List<Persona> listaPersonas(){
        return services.listaPersonas();

    }
}
