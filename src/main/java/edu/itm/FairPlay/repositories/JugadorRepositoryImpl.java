package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Jugador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JugadorRepositoryImpl implements JugadorRepository {

    private final JdbcTemplate jdbcTemplate;

    public JugadorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarJugador(Jugador jugador) throws Exception {
        String sql = "INSERT INTO Jugador (nombre, apellido, fechaRegistro, idPosicion) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, jugador.getNombre(), jugador.getApellido(), jugador.getFechaRegistro(), jugador.getIdPosicion());
    }
    @Override
    public List<Jugador> listarJugadores() throws Exception {
        String sql = "SELECT * FROM Jugador";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Jugador j = new Jugador();
            j.setIdJugador(rs.getInt("idjugador")); // <--- Con J mayúscula
            j.setNombre(rs.getString("nombre"));
            j.setApellido(rs.getString("apellido"));
            j.setFechaRegistro(rs.getDate("fechaRegistro"));
            j.setIdPosicion(rs.getInt("idPosicion"));
            return j;
        });
    }

    @Override
    public void actualizarJugador(Jugador jugador) throws Exception {
        String sql = "UPDATE Jugador SET nombre = ?, apellido = ?, fechaRegistro = ?, idPosicion = ? WHERE idjugador = ?";
        jdbcTemplate.update(sql, jugador.getNombre(), jugador.getApellido(), jugador.getFechaRegistro(), jugador.getIdPosicion(), jugador.getIdJugador()); // <--- Con J mayúscula
    }

    @Override
    public void eliminarJugador(Integer id) throws Exception {
        String sql = "DELETE FROM Jugador WHERE idjugador = ?";
        jdbcTemplate.update(sql, id);
    }}

