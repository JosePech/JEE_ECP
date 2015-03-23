package webServices.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import persistencia.models.daos.VotoDAO;
import persistencia.models.daos.jpa.DaoJpaFactory;
import persistencia.models.entities.Tema;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;
import persistencia.models.entities.utils.VotoSummary;

public class VotosRestTest {

    private List<Voto> votos;

    private VotoDAO dao;

    private TemaDAO temaDao;

    private Tema tema;

    private WebTarget wt;

    @Test
    public void createTest() {
        Voto voto = new Voto(tema, 5, Escolaridad.BACHILLERATO, "127.0.0.0");
        Response response = wt.request().post(Entity.xml(voto));
        voto = response.readEntity(Voto.class);
        assertEquals(201, response.getStatus());
        assertNotNull(voto.getId());
        assertFalse(voto.getId() == 0);
    }

    @Test
    public void consultarTest() {
        WebTarget webTarget = wt.path("consultar");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        List<VotoSummary> list = response.readEntity(new GenericType<List<VotoSummary>>() {
        });
        response.close();
        assertEquals(3, list.size());
    }

    @Test
    public void consultarTestParams1() {
        WebTarget webTarget = wt.path("consultar");
        webTarget = webTarget.queryParam("size", 2).queryParam("start", 1);
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        List<VotoSummary> list = response.readEntity(new GenericType<List<VotoSummary>>() {
        });
        response.close();
        assertEquals(2, list.size());
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DaoFactory.setFactory(new DaoJpaFactory());
        DaoJpaFactory.dropAndCreateTables();
    }

    @Before
    public void setUp() throws Exception {
        wt = ClientBuilder.newClient().target("http://localhost:8080/votacionApp/rest")
                .path("Votos");
        this.temaDao = DaoFactory.getFactory().getTemaDao();
        this.tema = new Tema("vacaciones", "Que tanto te gusta Italia?");
        temaDao.create(this.tema);

        dao = DaoFactory.getFactory().getVotoDao();
        this.votos = new ArrayList<Voto>();
        votos.add(new Voto(this.tema, 5, Escolaridad.BACHILLERATO, "192.168.1.80"));
        votos.add(new Voto(this.tema, 7, Escolaridad.SECUNDARIA, "192.168.1.20"));
        votos.add(new Voto(this.tema, 9, Escolaridad.PRIMARIA, "10.69.43.80"));
        for (Voto voto : this.votos) {
            dao.create(voto);
        }
    }

    @After
    public void tearDown() throws Exception {
        wt = null;
        this.temaDao.deleteById(this.tema.getId());
    }
}
