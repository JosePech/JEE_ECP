package rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.daos.VotoDAO;
import persistencia.models.daos.jpa.DaoJpaFactory;
import persistencia.models.entities.Tema;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;

public class TemasRestTest {
    
    private WebTarget wt;
    private TemaDAO dao;
    private VotoDAO votoDao;
    private Tema tema;
    
    @Test
    public void createTest() {
        tema = new Tema("Descripcion", "Pregunta");
        Response response = wt.request().post(Entity.xml(tema));
        tema = response.readEntity(Tema.class);
        assertEquals(201,response.getStatus());
        assertNotNull(tema.getId());
        assertFalse(tema.getId() == 0);
    }

    @Test
    public void deleteTest() {
        this.tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        dao.create(this.tema);
        
        votoDao = DaoFactory.getFactory().getVotoDao();        
        List<Voto> votos = new ArrayList<Voto>(); 
        votos.add(new Voto(this.tema, 5, Escolaridad.BACHILLERATO, "192.168.1.80"));
        votos.add(new Voto(this.tema, 7, Escolaridad.SECUNDARIA, "192.168.1.20"));
        votos.add(new Voto(this.tema, 9, Escolaridad.PRIMARIA, "10.69.43.80"));
        for(Voto voto : votos){
            votoDao.create(voto);
        } 
        
        Response response = wt.path(tema.getId().toString()).request().delete();
        assertEquals(response.getStatus(), 204);
        assertNull(dao.read(tema.getId()));
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DaoFactory.setFactory(new DaoJpaFactory());
        DaoJpaFactory.dropAndCreateTables();
    }

    @Before
    public void setUp() throws Exception {
        wt = ClientBuilder.newClient().target("http://localhost:8080/votacionApp/rest").path("Temas");
        this.dao = DaoFactory.getFactory().getTemaDao();
    }

    @After
    public void tearDown() throws Exception {
        wt = null;
        this.dao.deleteById(this.tema.getId());
    }

}


