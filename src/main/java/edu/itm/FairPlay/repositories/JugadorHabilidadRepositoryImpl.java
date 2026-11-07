package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.JugadorHabilidad;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JugadorHabilidadRepositoryImpl implements JugadorHabilidadRepository {

    private final JdbcTemplate jdbcTemplate;

    public JugadorHabilidadRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarJugadorHabilidad(JugadorHabilidad jh) throws Exception {
        String sql = "INSERT INTO JugadorHabilidad (idJugador, idHabilidad, valor) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, jh.getIdJugador(), jh.getIdHabilidad(), jh.getValor());
    }

    @Override
    public List<JugadorHabilidad> listarJugadorHabilidades() throws Exception {
        // Consulta explícita sin asteriscos (*)
        String sql = "SELECT idJugadorHabilidad, idJugador, idHabilidad, valor FROM JugadorHabilidad";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            JugadorHabilidad jh = new JugadorHabilidad();
            jh.setIdJugadorHabilidad(rs.getInt("idJugadorHabilidad"));
            jh.setIdJugador(rs.getInt("idJugador"));
            jh.setIdHabilidad(rs.getInt("idHabilidad"));
            jh.setValor(rs.getInt("valor"));
            return jh;
        });
    }

    @Override
    public JugadorHabilidad buscarPorId(Integer id) throws Exception {
        String sql = "SELECT idJugadorHabilidad, idJugador, idHabilidad, valor FROM JugadorHabilidad WHERE idJugadorHabilidad = ?";
        List<JugadorHabilidad> resultados = jdbcTemplate.query(sql, (rs, rowNum) -> {
            JugadorHabilidad jh = new JugadorHabilidad();
            jh.setIdJugadorHabilidad(rs.getInt("idJugadorHabilidad"));
            jh.setIdJugador(rs.getInt("idJugador"));
            jh.setIdHabilidad(rs.getInt("idHabilidad"));
            jh.setValor(rs.getInt("valor"));
            return jh;
        }, id);

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void actualizarJugadorHabilidad(JugadorHabilidad jh) throws Exception {
        String sql = "UPDATE JugadorHabilidad SET idJugador = ?, idHabilidad = ?, valor = ? WHERE idJugadorHabilidad = ?";
        jdbcTemplate.update(sql, jh.getIdJugador(), jh.getIdHabilidad(), jh.getValor(), jh.getIdJugadorHabilidad());
    }

    @Override
    public void eliminarJugadorHabilidad(Integer id) throws Exception {
        String sql = "DELETE FROM JugadorHabilidad WHERE idJugadorHabilidad = ?";
        jdbcTemplate.update(sql, id);
    }
}