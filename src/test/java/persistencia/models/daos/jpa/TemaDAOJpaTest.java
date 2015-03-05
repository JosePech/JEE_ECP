package persistencia.models.daos.jpa;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.entities.Tema;

public class TemaDAOJpaTest {
    
    private Tema tema;
    private TemaDAO dao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DaoFactory.setFactory(new DaoJpaFactory());
        DaoJpaFactory.dropAndCreateTables();
    }

    @Before
    public void setUp() throws Exception {
        this.tema = new Tema("vacaciones", "Cual es tu destino favorito?");
        dao = DaoFactory.getFactory().getTemaDao();
        dao.create(tema);
    }

    @After
    public void tearDown() throws Exception {
        DaoJpaFactory.dropAndCreateTables();
    }

    @Test
    public void testRead() {
        assertEquals(tema, dao.read(tema.getId()));
    }
    
    @Test
    public void testUpdate() {
        tema.setNombre("Viajes");
        tema.setPregunta("Cual es tu pais favorito?");
        dao.update(tema);
        Tema temaUpdated = dao.read(tema.getId());
        assertEquals(tema.getNombre(), temaUpdated.getNombre());
        assertEquals(tema.getPregunta(), temaUpdated.getPregunta());
    }
    
    @Test
    public void testDeleteById() {
        dao.deleteById(tema.getId());
        assertEquals(null, dao.read(tema.getId()));
    }
    
    @Test
    public void testFindAll() {
        dao.create(new Tema("Colores", "Cual es tu color favorito?"));
        assertEquals(2, dao.findAll().size());
    }
}
