package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Jugador;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JugadorRepositoryImpl implements JugadorRepository {

    private final JdbcTemplate jdbcTemplate;

    public JugadorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final RowMapper<Jugador> jugadorRowMapper = (rs, rowNum) -> {
        Jugador j = new Jugador();
        j.setIdJugador(rs.getInt("idJugador"));
        j.setNombre(rs.getString("nombre"));
        j.setApellido(rs.getString("apellido"));
        j.setIdPosicion(rs.getInt("idPosicion"));
        j.setFechaRegistro(rs.getDate("fechaRegistro"));
        return j;
    };

    @Override
    public void registrarJugador(Jugador jugador) throws Exception {
        String sql = "INSERT INTO Jugador (nombre, apellido, fechaRegistro, idPosicion) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, jugador.getNombre(), jugador.getApellido(), jugador.getFechaRegistro(), jugador.getIdPosicion());
        } catch (DataAccessException e) {
            throw new Exception("Error al registrar el jugador en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public List<Jugador> listarJugadores() throws Exception {
        // SQL Optimizado: Cero asteriscos. Nombramos las columnas requeridas.
        String sql = "SELECT idJugador, nombre, apellido, fechaRegistro, idPosicion FROM Jugador";
        try {
            return jdbcTemplate.query(sql, jugadorRowMapper);
        } catch (DataAccessException e) {
            throw new Exception("Error al listar los jugadores: " + e.getMessage());
        }
    }

    @Override
    public void actualizarJugador(Jugador jugador) throws Exception {
        String sql = "UPDATE Jugador SET nombre = ?, apellido = ?, fechaRegistro = ?, idPosicion = ? WHERE idJugador = ?";
        try {
            int filasAfectadas = jdbcTemplate.update(sql, jugador.getNombre(), jugador.getApellido(), jugador.getFechaRegistro(), jugador.getIdPosicion(), jugador.getIdJugador());
            if (filasAfectadas == 0) {
                throw new Exception("No se encontró el jugador con ID " + jugador.getIdJugador() + " para actualizar.");
            }
        } catch (DataAccessException e) {
            throw new Exception("Error al actualizar el jugador: " + e.getMessage());
        }
    }

    @Override
    public void eliminarJugador(Integer id) throws Exception {
        String sql = "DELETE FROM Jugador WHERE idJugador = ?";
        try {
            int filasAfectadas = jdbcTemplate.update(sql, id);
            if (filasAfectadas == 0) {
                throw new Exception("No se puede eliminar: El jugador con ID " + id + " no existe.");
            }
        } catch (DataAccessException e) {
            throw new Exception("Error al eliminar el jugador (verifique si está asociado a un equipo): " + e.getMessage());
        }
    }

    @Override
    public Jugador buscarPorId(Integer id) throws Exception {
        String sql = "SELECT idJugador, nombre, apellido, fechaRegistro, idPosicion FROM Jugador WHERE idJugador = ?";
        try {
            List<Jugador> resultados = jdbcTemplate.query(sql, jugadorRowMapper, id);
            return resultados.isEmpty() ? null : resultados.get(0);
        } catch (DataAccessException e) {
            throw new Exception("Error al buscar el jugador por ID: " + e.getMessage());
        }
    }
}