package persistencia.models.daos.jpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.daos.VotoDAO;
import persistencia.models.entities.Tema;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;
import persistencia.models.entities.utils.VotoSummary;

public class VotoDAOJpaTest {

    private List<Voto> votos;
    private VotoDAO dao;
    private TemaDAO temaDao;
    private Tema tema;
    private static final double DELTA = 1e-15;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DaoFactory.setFactory(new DaoJpaFactory());
        DaoJpaFactory.dropAndCreateTables();
    }

    @Before
    public void setUp() throws Exception {
        
        this.temaDao = DaoFactory.getFactory().getTemaDao();
        this.tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        temaDao.create(this.tema);
        
        dao = DaoFactory.getFactory().getVotoDao();        
        this.votos = new ArrayList<Voto>(); 
        votos.add(new Voto(this.tema, 5, Escolaridad.BACHILLERATO, "192.168.1.80"));
        votos.add(new Voto(this.tema, 7, Escolaridad.BACHILLERATO, "192.168.1.20"));
        votos.add(new Voto(this.tema, 9, Escolaridad.BACHILLERATO, "10.69.43.80"));
        for(Voto voto : this.votos){
            dao.create(voto);
        }     
    }
    
    @After
    public void tearDown() throws Exception {
        this.temaDao.deleteById(this.tema.getId());
    }
    
    @Test
    public void testCreateDuplicate() {
        dao.create(new Voto(this.tema, 5, Escolaridad.BACHILLERATO, "192.168.1.80"));
        assertEquals(3, dao.findAll().size());
    }
    
    @Test
    public void testSummary() {
        List<VotoSummary> summaries = dao.getSummary();
        VotoSummary vs = summaries.get(0);
        assertEquals(7, vs.getMedia(), DELTA);
        assertEquals(3, summaries.get(0).getTotal(), DELTA);
    }
    
    @Test
    public void testRead() {
        for(Voto voto : this.votos){
            assertEquals(voto, dao.read(voto.getId()));
        }        
    }
    
    @Test
    public void testUpdate() {
        Voto voto = dao.read(this.votos.get(0).getId());
        voto.setValor(3);
        voto.setIp("10.10.10.10");
        dao.update(voto);
        
        Voto votoUpdated = dao.read(voto.getId());
        assertEquals(voto.getIp(), votoUpdated.getIp());
        assertEquals(voto.getValor(), votoUpdated.getValor());
    }
    
    @Test
    public void testDeleteById() {
        Voto voto = dao.read(this.votos.get(0).getId());        
        dao.deleteById(voto.getId());
        assertNull(dao.read(tema.getId()));
        assertEquals(2, dao.findAll().size());
        assertEquals(this.tema, temaDao.read(this.tema.getId()));
    }
    
    @Test
    public void testFindAll() {
        List<Voto> votosPersistentes = dao.findAll();
        assertTrue(votosPersistentes.containsAll(this.votos));
    }
}
