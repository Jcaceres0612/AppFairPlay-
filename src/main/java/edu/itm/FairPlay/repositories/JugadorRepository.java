package edu.itm.FairPlay.repositories;

import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.itm.FairPlay.models.Jugador;

@Repository
public class JugadorRepository {

    private final DataSource dataSource;

    public JugadorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // CREATE (POST)
    public void registrarJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO Jugador (nombre, apellido, fechaRegistro, idPosicion) VALUES (?, ?, ?, ?)";

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, jugador.getNombre());
            statement.setString(2, jugador.getApellido());
            statement.setDate(3, jugador.getFechaRegistro());
            statement.setInt(4, jugador.getIdPosicion());
            statement.executeUpdate();
        }
    }

    // LIST (GET)
    public List<Jugador> listarJugadores() throws SQLException {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Jugador";

        try (Connection conexion = dataSource.getConnection();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt("idjugador"));
                j.setNombre(rs.getString("nombre"));
                j.setApellido(rs.getString("apellido"));
                j.setFechaRegistro(rs.getDate("fechaRegistro"));
                j.setIdPosicion(rs.getInt("idPosicion"));
                lista.add(j);
            }
        }
        return lista;
    }

    // UPDATE (PUT) - La 'U' de tu CRUL con DataSource
    public void actualizarJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE jugador SET nombre = ?, apellido = ?, idPosicion = ? WHERE idJugador = ?";

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, jugador.getNombre());
            statement.setString(2, jugador.getApellido());
            statement.setInt(3, jugador.getIdPosicion());
            statement.setInt(4, jugador.getIdJugador());
            statement.executeUpdate();
        }
    }
    // DELETE (Eliminar)
    public void eliminarJugador(Integer id) throws SQLException {
        String sql = "DELETE FROM Jugador WHERE idjugador = ?";

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
