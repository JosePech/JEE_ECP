package controladores.ejbs;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.entities.Tema;
import controladores.AgregarTemaController;

public class AgregarControllerEjbs implements AgregarTemaController {

    @Override
    public void agregar(Tema tema) {
        assert tema != null;
        TemaDAO dao = DaoFactory.getFactory().getTemaDao();
        dao.create(tema);
    }

}
