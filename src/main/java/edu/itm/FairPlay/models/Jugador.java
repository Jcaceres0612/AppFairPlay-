package edu.itm.FairPlay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "Jugador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugador")
    private Integer idjugador;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;

    @Column(name = "idPosicion")
    private Integer idPosicion;
}