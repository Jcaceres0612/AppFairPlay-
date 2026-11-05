package edu.itm.FairPlay.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*; // <--- Importamos el "Gerente Mágico" de JPA
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Table(name = "Jugador") // El nombre exacto de la tabla en tu base de datos
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Jugador {

    @Id // indicamos que esta es la Llave Primaria (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // aca le dejamos saber que es autoincremento
    @Column(name = "idJugador") // Conecta la variable con la columna exacta
    private Integer idJugador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "idPosicion")
    private Integer idPosicion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;
}