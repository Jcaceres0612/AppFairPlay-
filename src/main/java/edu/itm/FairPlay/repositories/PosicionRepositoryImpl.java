package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.Posicion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PosicionRepositoryImpl implements PosicionRepository {

    private final JdbcTemplate jdbcTemplate;

    // Spring inyecta JdbcTemplate automáticamente para que podamos hacer consultas
    public PosicionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarPosicion(Posicion posicion) throws Exception {
        String sql = "INSERT INTO Posicion (nombrePosicion, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, posicion.getNombrePosicion(), posicion.getDescripcion());
    }

    @Override
    public List<Posicion> listarPosiciones() throws Exception {
        String sql = "SELECT * FROM Posicion";

        return jdbcTemplate.query(sql, new RowMapper<Posicion>() {
            @Override
            public Posicion mapRow(ResultSet rs, int rowNum) throws SQLException {
                Posicion p = new Posicion();
                p.setIdPosicion(rs.getInt("idPosicion"));
                p.setNombrePosicion(rs.getString("nombrePosicion"));
                p.setDescripcion(rs.getString("descripcion"));
                return p;
            }
        });
    }

    @Override
    public void actualizarPosicion(Posicion posicion) throws Exception {
        String sql = "UPDATE Posicion SET nombrePosicion = ?, descripcion = ? WHERE idPosicion = ?";
        jdbcTemplate.update(sql, posicion.getNombrePosicion(), posicion.getDescripcion(), posicion.getIdPosicion());
    }

    @Override
    public void eliminarPosicion(Integer id) throws Exception {
        String sql = "DELETE FROM Posicion WHERE idPosicion = ?";
        jdbcTemplate.update(sql, id);
    }
}