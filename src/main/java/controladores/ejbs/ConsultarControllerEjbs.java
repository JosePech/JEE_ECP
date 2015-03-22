package controladores.ejbs;

import java.util.ArrayList;
import java.util.Comparator;
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

    private List<VotoSummary> summary;

    @Override
    public List<VotoSummary> consultar() {
        VotoDAO dao = DaoFactory.getFactory().getVotoDao();
        TemaDAO temaDao = DaoFactory.getFactory().getTemaDao();
        List<VotoSummary> votosRegistrados = dao.getSummary();
        summary = new ArrayList<VotoSummary>();

        for (Tema tema : temaDao.findAll()) {
            for (Escolaridad e : Escolaridad.values()) {

                List<VotoSummary> temp = votosRegistrados.stream().filter(existeVoto(tema, e))
                        .sorted(porTema().thenComparing(porEscolaridad()))
                        .collect(Collectors.toList());

                if (temp.size() > 0 && temp.get(0) != null) {
                    summary.add(temp.get(0));
                } else {
                    summary.add(new VotoSummary(tema, e, null, null));
                }
            }
        }

        return summary;
    }

    @Override
    public Long getTotalTema(Tema tema) {
        if (summary == null)
            return null;
        Long total = summary.stream().filter(votosPorTema(tema)).mapToLong(VotoSummary::getTotal)
                .sum();
        return total;
    }

    private Predicate<VotoSummary> existeVoto(Tema tema, Escolaridad e) {
        return p -> p.getTema().equals(tema) && p.getEscolaridad() == e;
    }

    private Predicate<VotoSummary> votosPorTema(Tema tema) {
        return p -> p.getTema().equals(tema) && p.getTotal() != null;
    }

    private Comparator<VotoSummary> porTema() {
        return (t1, t2) -> t1.getTema().getNombre().compareTo(t2.getTema().getNombre());
    }

    private Comparator<VotoSummary> porEscolaridad() {
        return (t1, t2) -> t1.getEscolaridad().toString().compareTo(t2.getEscolaridad().toString());
    }
}
