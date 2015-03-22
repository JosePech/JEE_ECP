package persistencia.models.daos.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import persistencia.models.daos.TemaDAO;
import persistencia.models.entities.Tema;

public class TemaDAOJdbc extends GenericDAOJdbc<Tema, Integer> implements TemaDAO {

    private Logger log = LogManager.getLogger(TemaDAOJdbc.class);

    private Tema create(ResultSet resultSet) {
        Tema tema;
        try {
            if (resultSet != null && resultSet.next()) {
                tema = new Tema(resultSet.getString(Tema.NOMBRE),
                        resultSet.getString(Tema.PREGUNTA));
                tema.setId(resultSet.getInt(Tema.ID));
                return tema;
            }
        } catch (SQLException e) {
            log.error("read: " + e.getMessage());
        }
        return null;
    }

    private static final String SQL_CREATE_TABLE = "CREATE TABLE %s (%s INT NOT NULL AUTO_INCREMENT, %s VARCHAR(255), "
            + "%s VARCHAR(255), PRIMARY KEY (%s) )";

    public static String sqlToCreateTable() {
        return String.format(SQL_CREATE_TABLE, Tema.TABLE, Tema.ID, Tema.NOMBRE, Tema.PREGUNTA,
                Tema.ID);
    }

    private static final String SQL_INSERT = "INSERT INTO %s (%s,%s) VALUES ('%s','%s')";

    @Override
    public void create(Tema tema) {
        assert tema.getNombre() != null;
        assert tema.getPregunta() != null;

        this.updateSql(String.format(SQL_INSERT, Tema.TABLE, Tema.NOMBRE, Tema.PREGUNTA,
                tema.getNombre(), tema.getPregunta()));
        tema.setId(this.autoId());
    }

    @Override
    public Tema read(Integer id) {
        ResultSet resultSet = this.query(String.format(SQL_SELECT_ID, Tema.TABLE, id));
        return this.create(resultSet);
    }

    private static final String SQL_UPDATE = "UPDATE %s SET %s='%s', %s='%s' WHERE ID=%d";

    @Override
    public void update(Tema tema) {
        assert tema.getNombre() != null;
        assert tema.getPregunta() != null;

        this.updateSql(String.format(SQL_UPDATE, Tema.TABLE, Tema.NOMBRE, tema.getNombre(),
                Tema.PREGUNTA, tema.getPregunta(), tema.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        Tema tema = this.read(id);
        if (tema != null) {
            this.updateSql(String.format(SQL_DELETE_ID, Tema.TABLE, id));
        }
    }

    @Override
    public List<Tema> findAll() {
        List<Tema> list = new ArrayList<Tema>();
        ResultSet resultSet = this.query(String.format(SQL_SELECT_ALL, Tema.TABLE));
        Tema tema = this.create(resultSet);
        while (tema != null) {
            list.add(tema);
            tema = this.create(resultSet);
        }
        return list;
    }

}
