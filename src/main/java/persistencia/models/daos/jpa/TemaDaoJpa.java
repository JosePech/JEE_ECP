package persistencia.models.daos.jpa;

import persistencia.models.daos.TemaDAO;
import persistencia.models.entities.Tema;

public class TemaDaoJpa extends GenericDaoJpa<Tema, Integer> implements TemaDAO {

    public TemaDaoJpa() {
        super(Tema.class);
    }

}
