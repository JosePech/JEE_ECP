package rest;

import static org.junit.Assert.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistencia.models.entities.utils.Escolaridad;

public class UtilsRestTest {

    private WebTarget wt;

    @Before
    public void setUp() throws Exception {
        wt = ClientBuilder.newClient().target("http://localhost:8080/votacionApp/rest")
                .path("Utils");
    }

    @After
    public void tearDown() throws Exception {
        wt = null;
    }

    @Test
    public void escolaridadTest() {
        WebTarget webTarget = wt.path("escolaridad");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Escolaridad[] list = response.readEntity(new GenericType<Escolaridad[]>() {
        });
        response.close();
        assertEquals(4, list.length);
    }

}
