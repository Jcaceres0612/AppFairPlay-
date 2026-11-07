package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Habilidad;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HabilidadRepositoryImpl implements HabilidadRepository {

    private final JdbcTemplate jdbcTemplate;

    public HabilidadRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarHabilidad(Habilidad habilidad) throws Exception {
        String sql = "INSERT INTO Habilidad (nombreHabilidad, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, habilidad.getNombreHabilidad(), habilidad.getDescripcion());
    }

    @Override
    public List<Habilidad> listarHabilidades() throws Exception {
        // Consulta explícita sin asteriscos (*)
        String sql = "SELECT idHabilidad, nombreHabilidad, descripcion FROM Habilidad";

        return jdbcTemplate.query(sql, new RowMapper<Habilidad>() {
            @Override
            public Habilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
                Habilidad h = new Habilidad();
                h.setIdHabilidad(rs.getInt("idHabilidad"));
                h.setNombreHabilidad(rs.getString("nombreHabilidad"));
                h.setDescripcion(rs.getString("descripcion"));
                return h;
            }
        });
    }

    @Override
    public void actualizarHabilidad(Habilidad habilidad) throws Exception {
        String sql = "UPDATE Habilidad SET nombreHabilidad = ?, descripcion = ? WHERE idHabilidad = ?";
        jdbcTemplate.update(sql, habilidad.getNombreHabilidad(), habilidad.getDescripcion(), habilidad.getIdHabilidad());
    }

    @Override
    public void eliminarHabilidad(Integer id) throws Exception {
        String sql = "DELETE FROM Habilidad WHERE idHabilidad = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Habilidad buscarPorId(Integer id) throws Exception {
        String sql = "SELECT idHabilidad, nombreHabilidad, descripcion FROM Habilidad WHERE idHabilidad = ?";

        List<Habilidad> resultados = jdbcTemplate.query(sql, new RowMapper<Habilidad>() {
            @Override
            public Habilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
                Habilidad h = new Habilidad();
                h.setIdHabilidad(rs.getInt("idHabilidad"));
                h.setNombreHabilidad(rs.getString("nombreHabilidad"));
                h.setDescripcion(rs.getString("descripcion"));
                return h;
            }
        }, id);

        if (resultados.isEmpty()) {
            return null;
        } else {
            return resultados.get(0);
        }
    }
}