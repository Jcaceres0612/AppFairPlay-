package edu.itm.FairPlay.controllers;

import edu.itm.FairPlay.identities.Persona;
import edu.itm.FairPlay.services.PersonaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 import java.util.List;

    @RestController


public class PersonasController<exception extends Throwable> {
    private final PersonaServices service;


        public PersonasController(PersonaServices service) {
            this.service = service;
        }


        @GetMapping("listapersonas")
        public ResponseEntity<List<Persona>> ListarPersonas() throws exception {
ResponseEntity<List<Persona>> responseEntity;
            responseEntity = new ResponseEntity<>(service.listaPersonas(), HttpStatus.OK);


            return responseEntity;
        }

}