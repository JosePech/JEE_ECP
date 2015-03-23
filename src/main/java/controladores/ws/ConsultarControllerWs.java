package controladores.ws;

import java.util.List;

import javax.ws.rs.core.GenericType;

import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.VotoSummary;
import webServices.VotoUris;
import controladores.ConsultarController;

public class ConsultarControllerWs extends ConsultarController {

    @Override
    public List<VotoSummary> consultar() {
        List<VotoSummary> votosRegistrados = ControllerWs.buildWebServiceManager(
                VotoUris.PATH_CONSULTAR).entities(new GenericType<List<VotoSummary>>() {
        });
        List<Tema> temas = new TemasControllerWs().getTemas();
        return this.llenarRegistrosVacios(temas, votosRegistrados);
    }

    @Override
    public Long getTotalTema(Tema tema) {
        Long total = this.consultar().stream().filter(votosPorTema(tema))
                .mapToLong(VotoSummary::getTotal).sum();
        return total;
    }

}
