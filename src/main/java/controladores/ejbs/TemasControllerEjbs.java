package controladores.ejbs;

import java.util.List;

import persistencia.models.daos.DaoFactory;
import persistencia.models.entities.Tema;
import controladores.TemasController;

public class TemasControllerEjbs implements TemasController {

    @Override
    public List<Tema> getTemas() {
        return DaoFactory.getFactory().getTemaDao().findAll();
    }

}
