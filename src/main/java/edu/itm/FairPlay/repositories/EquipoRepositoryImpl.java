package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Equipo;
import edu.itm.FairPlay.models.Partido;
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
        // Obtenemos el ID desde el objeto Partido asociado
        jdbcTemplate.update(sql, equipo.getNombreEquipo(), equipo.getPartido().getIdPartido());
    }

    @Override
    public List<Equipo> listarEquipos() throws Exception {
        // Consulta explícita sin asteriscos (*) cumpliendo los requisitos
        String sql = "SELECT idEquipo, nombreEquipo, idPartido FROM Equipo";

        return jdbcTemplate.query(sql, new RowMapper<Equipo>() {
            @Override
            public Equipo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("idEquipo"));
                e.setNombreEquipo(rs.getString("nombreEquipo"));

                // Mapeo correcto del objeto Partido relacionado
                Partido p = new Partido();
                p.setIdPartido(rs.getInt("idPartido"));
                e.setPartido(p);

                return e;
            }
        });
    }

    @Override
    public void actualizarEquipo(Equipo equipo) throws Exception {
        String sql = "UPDATE Equipo SET nombreEquipo = ?, idPartido = ? WHERE idEquipo = ?";
        jdbcTemplate.update(sql,
                equipo.getNombreEquipo(),
                equipo.getPartido().getIdPartido(),
                equipo.getIdEquipo()
        );
    }

    @Override
    public void eliminarEquipo(Integer id) throws Exception {
        String sql = "DELETE FROM Equipo WHERE idEquipo = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Equipo buscarPorId(Integer id) throws Exception {
        // Consulta explícita sin asteriscos (*)
        String sql = "SELECT idEquipo, nombreEquipo, idPartido FROM Equipo WHERE idEquipo = ?";

        List<Equipo> resultados = jdbcTemplate.query(sql, new RowMapper<Equipo>() {
            @Override
            public Equipo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("idEquipo"));
                e.setNombreEquipo(rs.getString("nombreEquipo"));

                Partido p = new Partido();
                p.setIdPartido(rs.getInt("idPartido"));
                e.setPartido(p);

                return e;
            }
        }, id);

        if (resultados.isEmpty()) {
            return null; // El equipo no existe
        } else {
            return resultados.get(0); // Devolvemos el equipo encontrado
        }
    }
}