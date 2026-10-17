package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Habilidad;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // ¡Esta es la etiqueta que Spring Boot estaba pidiendo a gritos!
public class HabilidadRepositoryImpl implements HabilidadRepository {

    private final JdbcTemplate jdbcTemplate;

    // El constructor donde Spring inyecta la conexión a la base de datos
    public HabilidadRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarHabilidad(Habilidad habilidad) throws Exception {
        // La consulta SQL (El signo de interrogación evita ataques de inyección SQL)
        String sql = "INSERT INTO Habilidad (nombre) VALUES (?)";
        jdbcTemplate.update(sql, habilidad.getNombreHabilidad());
    }

    @Override
    public List<Habilidad> listarHabilidades() throws Exception {
        String sql = "SELECT * FROM Habilidad";

        return jdbcTemplate.query(sql, new RowMapper<Habilidad>() {
            @Override
            public Habilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
                Habilidad h = new Habilidad();
                // Ojo: Asegúrate de que estos nombres en comillas sean idénticos a los de tu tabla SQL
                h.setIdHabilidad(rs.getInt("idHabilidad"));
                h.setNombreHabilidad(rs.getString("nombre"));
                return h;
            }
        });
    }

    @Override
    public void actualizarHabilidad(Habilidad habilidad) throws Exception {
        String sql = "UPDATE Habilidad SET nombre = ? WHERE idHabilidad = ?";
        jdbcTemplate.update(sql, habilidad.getNombreHabilidad(), habilidad.getIdHabilidad());
    }

    @Override
    public void eliminarHabilidad(Integer id) throws Exception {
        String sql = "DELETE FROM Habilidad WHERE idHabilidad = ?";
        jdbcTemplate.update(sql, id);
    }
}