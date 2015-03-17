package controladores.ejbs;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.VotoDAO;
import persistencia.models.entities.Voto;
import controladores.VotarController;

public class VotarControllerEjbs extends TemasControllerEjbs implements VotarController {

    @Override
    public void votar(Voto voto) {
        assert voto != null;
        VotoDAO dao = DaoFactory.getFactory().getVotoDao();
        dao.create(voto);
    }

}
