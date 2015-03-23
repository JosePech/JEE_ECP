package controladores;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.Escolaridad;
import persistencia.models.entities.utils.VotoSummary;

public abstract class ConsultarController {

    public abstract List<VotoSummary> consultar();

    public abstract Long getTotalTema(Tema tema);
    
    protected List<VotoSummary> llenarRegistrosVacios(List<Tema> temas, List<VotoSummary> votosRegistrados){
        List<VotoSummary> summary = new ArrayList<VotoSummary>();

        for (Tema tema : temas) {
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

    protected Predicate<VotoSummary> existeVoto(Tema tema, Escolaridad e) {
        return p -> p.getTema().equals(tema) && p.getEscolaridad() == e;
    }

    protected Predicate<VotoSummary> votosPorTema(Tema tema) {
        return p -> p.getTema().equals(tema) && p.getTotal() != null;
    }

    protected Comparator<VotoSummary> porTema() {
        return (t1, t2) -> t1.getTema().getNombre().compareTo(t2.getTema().getNombre());
    }

    protected Comparator<VotoSummary> porEscolaridad() {
        return (t1, t2) -> t1.getEscolaridad().toString().compareTo(t2.getEscolaridad().toString());
    }

}
