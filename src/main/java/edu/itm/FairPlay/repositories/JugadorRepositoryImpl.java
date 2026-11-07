package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Jugador;
import edu.itm.FairPlay.models.Posicion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JugadorRepositoryImpl {

    // URL, usuario y contraseña de tu conexión a SQL Server (ajusta si usas variables de entorno o application.properties)
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=FairPlay;encrypt=true;trustServerCertificate=true";
    private final String user = "tu_usuario"; // Cambia por tu usuario de SQL Server
    private final String password = "tu_password"; // Cambia por tu contraseña de SQL Server

    // Consulta SQL explícita sin asteriscos (*) cumpliendo con los requisitos
    public List<Jugador> obtenerTodosSql() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT idjugador, nombre, apellido, fechaRegistro, idPosicion FROM Jugador";

        // Uso de try-with-resources para asegurar el cierre automático de conexiones y prevenir fugas
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setIdjugador(rs.getInt("idjugador"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setApellido(rs.getString("apellido"));

                Date sqlDate = rs.getDate("fechaRegistro");
                if (sqlDate != null) {
                    jugador.setFechaRegistro(sqlDate.toLocalDate());
                }

                // Mapeo correcto de la posición como objeto para evitar errores
                Posicion posicion = new Posicion();
                posicion.setIdPosicion(rs.getInt("idPosicion"));
                jugador.setPosicion(posicion);

                jugadores.add(jugador);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar jugadores vía JDBC SQL", e);
        }

        return jugadores;
    }
}