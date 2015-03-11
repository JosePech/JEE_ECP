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

public class TemaDAOJpaTest {
    
    private List<Tema> temas;
    private TemaDAO dao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DaoFactory.setFactory(new DaoJpaFactory());
        DaoJpaFactory.dropAndCreateTables();
    }

    @Before
    public void setUp() throws Exception {
        dao = DaoFactory.getFactory().getTemaDao();        
        this.temas = new ArrayList<Tema>(); 
        temas.add(new Tema("vacaciones", "Cual es tu lugar favorito?"));
        temas.add(new Tema("colores", "Cual es tu color favorito?"));
        temas.add(new Tema("paises", "Cual es tu pais favorito?"));
        for(Tema tema : this.temas){
            dao.create(tema);
        }       
    }
    
    @After
    public void tearDown() throws Exception {
        for(Tema tema : this.temas){
            dao.deleteById(tema.getId());
        }
    }
    
    @Test
    public void testCreateDuplicate() {
        dao.create(new Tema("vacaciones", "Cual es tu lugar favorito?"));
        assertEquals(3, dao.findAll().size());
    }
    
    @Test
    public void testDeleteTemaVotos() {
        Tema tema = dao.read(this.temas.get(0).getId());
        
        Voto voto = new Voto(tema, 5, Escolaridad.PRIMARIA, "127.0.0.1");
        VotoDAO votoDao = DaoFactory.getFactory().getVotoDao();
        votoDao.create(voto);
        
        dao.deleteById(tema.getId());        
        assertEquals(null, dao.read(tema.getId()));
        assertEquals(0, votoDao.findAll().size());
        assertEquals(2, dao.findAll().size());
    }
    
    @Test
    public void testRead() {
        for(Tema tema : this.temas){
            assertEquals(tema, dao.read(tema.getId()));
        }        
    }
    
    @Test
    public void testUpdate() {
        Tema tema = dao.read(this.temas.get(0).getId());
        tema.setNombre("Viajes");
        tema.setPregunta("Cual es tu destino favorito?");
        dao.update(tema);
        
        Tema temaUpdated = dao.read(tema.getId());
        assertEquals(tema.getNombre(), temaUpdated.getNombre());
        assertEquals(tema.getPregunta(), temaUpdated.getPregunta());
    }
    
    @Test
    public void testDeleteById() {
        Tema tema = dao.read(this.temas.get(0).getId());
        dao.deleteById(tema.getId());
        assertEquals(null, dao.read(tema.getId()));
        assertEquals(2, dao.findAll().size());
    }
    
    @Test
    public void testFindAll() {
        List<Tema> temasPersistentes = dao.findAll();
        for(int i = 0; i < temasPersistentes.size(); i++ ){
            assertEquals(true, temasPersistentes.containsAll(this.temas));
        }
    }
}
