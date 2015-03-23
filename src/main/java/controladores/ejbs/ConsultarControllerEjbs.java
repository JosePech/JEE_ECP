package controladores.ejbs;

import java.util.List;

import persistencia.models.daos.DaoFactory;
import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.VotoSummary;
import controladores.ConsultarController;

public class ConsultarControllerEjbs extends ConsultarController {

    private List<VotoSummary> summary;

    @Override
    public List<VotoSummary> consultar() {
        List<VotoSummary> votosRegistrados = DaoFactory.getFactory().getVotoDao().getSummary();
        List<Tema> temas = DaoFactory.getFactory().getTemaDao().findAll();
        return this.llenarRegistrosVacios(temas, votosRegistrados);
    }

    @Override
    public Long getTotalTema(Tema tema) {
        if (summary == null)
            this.consultar();
        Long total = summary.stream().filter(votosPorTema(tema)).mapToLong(VotoSummary::getTotal)
                .sum();
        return total;
    }
}
