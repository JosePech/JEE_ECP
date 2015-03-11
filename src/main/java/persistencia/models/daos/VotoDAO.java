package persistencia.models.daos;

import java.util.List;

import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.VotoSummary;

public interface VotoDAO extends GenericDAO<Voto, Integer> {
    
    public List<VotoSummary> getSummary();
}
