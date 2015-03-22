package rest;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.daos.jpa.DaoJpaFactory;
import persistencia.models.entities.Tema;

public class TemasRestTest {

    private WebTarget wt;

    private TemaDAO dao;

    private Tema tema;

    @Test
    public void createTest() {
        tema = new Tema("Descripcion", "Pregunta");
        Response response = wt.request().post(Entity.xml(tema));
        tema = response.readEntity(Tema.class);
        assertEquals(201, response.getStatus());
        assertNotNull(tema.getId());
        assertFalse(tema.getId() == 0);
    }

    @Test
    public void deleteTest() {
        tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        dao.create(tema);
        Response response = wt.path(tema.getId().toString()).queryParam("clave", "666").request()
                .delete();
        assertEquals(204, response.getStatus());
    }

    @Test
    public void invalidDeleteTest() {
        tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        dao.create(tema);
        Response response = wt.path(tema.getId().toString()).request().delete();
        assertEquals(400, response.getStatus());
    }

    @Test
    public void consultarTest() {
        tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        dao.create(tema);
        WebTarget webTarget = wt;
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        List<Tema> list = response.readEntity(new GenericType<List<Tema>>() {
        });
        response.close();
        assertEquals(1, list.size());
    }

    @Test
    public void consultarTestParams() {
        tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        dao.create(tema);
        Tema tema2 = new Tema("vacaciones", "Que tanto te gusta Paris?");
        dao.create(tema2);

        WebTarget webTarget = wt;
        webTarget = webTarget.queryParam("size", 2).queryParam("start", 1);
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        List<Tema> list = response.readEntity(new GenericType<List<Tema>>() {
        });
        response.close();
        assertEquals(1, list.size());
        dao.deleteById(tema2.getId());
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DaoFactory.setFactory(new DaoJpaFactory());
        DaoJpaFactory.dropAndCreateTables();
    }

    @Before
    public void setUp() throws Exception {
        wt = ClientBuilder.newClient().target("http://localhost:8080/votacionApp/rest")
                .path("Temas");
        dao = DaoFactory.getFactory().getTemaDao();
    }

    @After
    public void tearDown() throws Exception {
        wt = null;
        dao.deleteById(tema.getId());
    }

}
