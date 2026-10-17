package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Equipo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EquipoRepositoryImpl implements EquipoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EquipoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarEquipo(Equipo equipo) throws Exception {
        String sql = "INSERT INTO Equipo (nombreEquipo, idPartido) VALUES (?, ?)";
        jdbcTemplate.update(sql, equipo.getNombreEquipo(), equipo.getIdPartido());
    }

    @Override
    public List<Equipo> listarEquipos() throws Exception {
        String sql = "SELECT * FROM Equipo";

        return jdbcTemplate.query(sql, new RowMapper<Equipo>() {
            @Override
            public Equipo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("idEquipo"));
                e.setNombreEquipo(rs.getString("nombreEquipo"));
                e.setIdPartido(rs.getInt("idPartido"));
                return e;
            }
        });
    }

    @Override
    public void actualizarEquipo(Equipo equipo) throws Exception {
        String sql = "UPDATE Equipo SET nombreEquipo = ?, idPartido = ? WHERE idEquipo = ?";
        jdbcTemplate.update(sql, equipo.getNombreEquipo(), equipo.getIdPartido(), equipo.getIdEquipo());
    }

    @Override
    public void eliminarEquipo(Integer id) throws Exception {
        String sql = "DELETE FROM Equipo WHERE idEquipo = ?";
        jdbcTemplate.update(sql, id);
    }
}