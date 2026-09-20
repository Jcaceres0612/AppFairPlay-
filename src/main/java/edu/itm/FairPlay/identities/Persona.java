package edu.itm.FairPlay.identities;


import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Persona {

    String nombre;
    String apellidos;
    String numero;
    String email;
    String direccion;
    String cedula;

}
