package persistencia.models.daos.jpa;

import persistencia.models.daos.VotoDAO;
import persistencia.models.entities.Voto;

public class VotoDaoJpa extends GenericDaoJpa<Voto, Integer> implements VotoDAO {

    public VotoDaoJpa() {
        super(Voto.class);
    }

}