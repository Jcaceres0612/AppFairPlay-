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
    public List<EquipoJugador> listarPorEquipo(Integer idEquipo) throws Exception {
        return null; // Lo llenaremos con el RowMapper más adelante
    }

    @Override
    public void eliminarEquipoJugador(Integer idEquipo, Integer idJugador) throws Exception {
        // Lo implementaremos más adelante
    }

    @Override
    public Integer contarJugadoresPorEquipo(Integer idEquipo) throws Exception {
        String sql = "SELECT COUNT(*) FROM EquipoJugador WHERE idEquipo = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idEquipo);
    }
}

