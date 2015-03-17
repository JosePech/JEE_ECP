package controladores.ejbs;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.entities.Tema;
import controladores.BorrarTemaController;

public class BorrarControllerEjbs extends TemasControllerEjbs implements BorrarTemaController {

    @Override
    public void borrar(Tema tema) {
        assert tema != null && tema.getId() != null;
        TemaDAO dao = DaoFactory.getFactory().getTemaDao();
        dao.deleteById(tema.getId());
    }
}
