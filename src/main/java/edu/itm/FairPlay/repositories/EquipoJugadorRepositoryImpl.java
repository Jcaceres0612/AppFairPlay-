package edu.itm.FairPlay.repositories;

import edu.itm.FairPlay.models.EquipoJugador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipoJugadorRepositoryImpl implements EquipoJugadorRepository {

    private final JdbcTemplate jdbcTemplate;

    public EquipoJugadorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registrarEquipoJugador(EquipoJugador equipoJugador) throws Exception {
        String sql = "INSERT INTO EquipoJugador (idEquipo, idJugador) VALUES (?, ?)";
        jdbcTemplate.update(sql, equipoJugador.getIdEquipo(), equipoJugador.getIdJugador());
    }

    @Override
    public List<EquipoJugador> listarEquipoJugadores() throws Exception {
        // Consulta explícita sin asteriscos (*)
        String sql = "SELECT idEquipoJugador, idEquipo, idJugador FROM EquipoJugador";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EquipoJugador ej = new EquipoJugador();
            ej.setIdEquipoJugador(rs.getInt("idEquipoJugador"));
            ej.setIdEquipo(rs.getInt("idEquipo"));
            ej.setIdJugador(rs.getInt("idJugador"));
            return ej;
        });
    }

    @Override
    public EquipoJugador buscarPorId(Integer id) throws Exception {
        String sql = "SELECT idEquipoJugador, idEquipo, idJugador FROM EquipoJugador WHERE idEquipoJugador = ?";
        List<EquipoJugador> resultados = jdbcTemplate.query(sql, (rs, rowNum) -> {
            EquipoJugador ej = new EquipoJugador();
            ej.setIdEquipoJugador(rs.getInt("idEquipoJugador"));
            ej.setIdEquipo(rs.getInt("idEquipo"));
            ej.setIdJugador(rs.getInt("idJugador"));
            return ej;
        }, id);

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void actualizarEquipoJugador(EquipoJugador equipoJugador) throws Exception {
        String sql = "UPDATE EquipoJugador SET idEquipo = ?, idJugador = ? WHERE idEquipoJugador = ?";
        jdbcTemplate.update(sql, equipoJugador.getIdEquipo(), equipoJugador.getIdJugador(), equipoJugador.getIdEquipoJugador());
    }

    @Override
    public void eliminarEquipoJugador(Integer id) throws Exception {
        String sql = "DELETE FROM EquipoJugador WHERE idEquipoJugador = ?";
        jdbcTemplate.update(sql, id);
    }
}
