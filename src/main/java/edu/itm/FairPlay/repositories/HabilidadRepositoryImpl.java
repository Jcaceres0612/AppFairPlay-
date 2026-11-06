package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Habilidad;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HabilidadRepositoryImpl implements HabilidadRepository {

    private final JdbcTemplate jdbcTemplate;

    public HabilidadRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- CLEAN CODE: Traductor extraído como expresión Lambda ---
    private final RowMapper<Habilidad> habilidadRowMapper = (rs, rowNum) -> {
        Habilidad h = new Habilidad();
        h.setIdHabilidad(rs.getInt("idHabilidad"));
        h.setNombreHabilidad(rs.getString("nombre")); // Mapea la columna 'nombre' al atributo 'nombreHabilidad'
        return h;
    };

    @Override
    public void registrarHabilidad(Habilidad habilidad) throws Exception {
        String sql = "INSERT INTO Habilidad (nombre) VALUES (?)";
        try {
            jdbcTemplate.update(sql, habilidad.getNombreHabilidad());
        } catch (DataAccessException e) {
            throw new Exception("Error al registrar la habilidad en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public List<Habilidad> listarHabilidades() throws Exception {
        // SQL Optimizado: Cambiamos el SELECT * por los nombres exactos de las columnas
        String sql = "SELECT idHabilidad, nombre FROM Habilidad";
        try {
            return jdbcTemplate.query(sql, habilidadRowMapper);
        } catch (DataAccessException e) {
            throw new Exception("Error al listar las habilidades: " + e.getMessage());
        }
    }

    @Override
    public void actualizarHabilidad(Habilidad habilidad) throws Exception {
        String sql = "UPDATE Habilidad SET nombre = ? WHERE idHabilidad = ?";
        try {
            int filasAfectadas = jdbcTemplate.update(sql, habilidad.getNombreHabilidad(), habilidad.getIdHabilidad());
            if (filasAfectadas == 0) {
                throw new Exception("No se encontró la habilidad con ID " + habilidad.getIdHabilidad() + " para actualizar.");
            }
        } catch (DataAccessException e) {
            throw new Exception("Error al actualizar la habilidad: " + e.getMessage());
        }
    }

    @Override
    public void eliminarHabilidad(Integer id) throws Exception {
        String sql = "DELETE FROM Habilidad WHERE idHabilidad = ?";
        try {
            int filasAfectadas = jdbcTemplate.update(sql, id);
            if (filasAfectadas == 0) {
                throw new Exception("No se puede eliminar: La habilidad con ID " + id + " no existe.");
            }
        } catch (DataAccessException e) {
            // Un error muy común aquí es que la habilidad ya esté asignada a un jugador (Violación de Llave Foránea)
            throw new Exception("Error al eliminar la habilidad (verifique si está en uso por algún jugador): " + e.getMessage());
        }
    }
}