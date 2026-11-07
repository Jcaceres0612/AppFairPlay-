package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Partido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PartidoRepositoryImpl implements PartidoRepository {

    private final JdbcTemplate jdbcTemplate;

    public PartidoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarPartido(Partido partido) throws Exception {
        String sql = "INSERT INTO Partido (fechaHora, tipoPartido, estado) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, partido.getFechaHora(), partido.getTipoPartido(), partido.getEstado());
    }

    @Override
    public List<Partido> listarPartidos() throws Exception {
        // Consulta explícita sin asteriscos (*)
        String sql = "SELECT idPartido, fechaHora, tipoPartido, estado FROM Partido";

        return jdbcTemplate.query(sql, new RowMapper<Partido>() {
            @Override
            public Partido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Partido p = new Partido();
                p.setIdPartido(rs.getInt("idPartido"));

                // Conversión segura de Timestamp a LocalDateTime
                LocalDateTime fechaHora = rs.getTimestamp("fechaHora") != null ?
                        rs.getTimestamp("fechaHora").toLocalDateTime() : null;
                p.setFechaHora(fechaHora);

                p.setTipoPartido(rs.getString("tipoPartido"));
                p.setEstado(rs.getString("estado"));
                return p;
            }
        });
    }

    @Override
    public void actualizarPartido(Partido partido) throws Exception {
        String sql = "UPDATE Partido SET fechaHora = ?, tipoPartido = ?, estado = ? WHERE idPartido = ?";
        jdbcTemplate.update(sql,
                partido.getFechaHora(),
                partido.getTipoPartido(),
                partido.getEstado(),
                partido.getIdPartido()
        );
    }

    @Override
    public void eliminarPartido(Integer id) throws Exception {
        String sql = "DELETE FROM Partido WHERE idPartido = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Partido buscarPorId(Integer id) throws Exception {
        // Consulta explícita sin asteriscos (*)
        String sql = "SELECT idPartido, fechaHora, tipoPartido, estado FROM Partido WHERE idPartido = ?";

        List<Partido> resultados = jdbcTemplate.query(sql, new RowMapper<Partido>() {
            @Override
            public Partido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Partido p = new Partido();
                p.setIdPartido(rs.getInt("idPartido"));

                LocalDateTime fechaHora = rs.getTimestamp("fechaHora") != null ?
                        rs.getTimestamp("fechaHora").toLocalDateTime() : null;
                p.setFechaHora(fechaHora);

                p.setTipoPartido(rs.getString("tipoPartido"));
                p.setEstado(rs.getString("estado"));
                return p;
            }
        }, id);

        if (resultados.isEmpty()) {
            return null;
        } else {
            return resultados.get(0);
        }
    }
}