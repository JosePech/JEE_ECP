package controladores.ejbs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.daos.VotoDAO;
import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.Escolaridad;
import persistencia.models.entities.utils.VotoSummary;
import controladores.ConsultarController;

public class ConsultarControllerEjbs implements ConsultarController {

    @Override
    public List<VotoSummary> consultar() {
        VotoDAO dao = DaoFactory.getFactory().getVotoDao();
        TemaDAO temaDao = DaoFactory.getFactory().getTemaDao();
        List<VotoSummary> votosRegistrados = dao.getSummary();
        List<VotoSummary> result = new ArrayList<VotoSummary>();
        //System.out.println(String.format("Registros[Size:%d]", votosRegistrados.size()));
        for (Tema tema : temaDao.findAll()) {
            for (Escolaridad e : Escolaridad.values()) {
                //System.out.println(String.format("Testing[Tema.Id:%s -> Escolaridad:%s]", tema.getId(), e));
                List<VotoSummary> temp = votosRegistrados.stream().filter(existeVoto(tema, e))
                        .collect(Collectors.toList());
                //System.out.println(String.format("Filtro[Size:%d]", temp.size()));
                assert temp.size() < 2;
                if (temp.size() > 0 && temp.get(0) != null) {
                    result.add(temp.get(0));
                } else {
                    result.add(new VotoSummary(tema, e, null, null));
                }
            }
        }
        return result;
    }

    private Predicate<VotoSummary> existeVoto(Tema tema, Escolaridad e) {
        return  p -> p.getTema().equals(tema) && p.getEscolaridad() == e;
    }
}
