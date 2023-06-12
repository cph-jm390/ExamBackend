package rest;

import entities.Assignment;
import entities.Dinnerevent;
import io.restassured.http.ContentType;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class DinnereventResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Dinnerevent d1, d2;
    private static List<Assignment> a1 = new ArrayList<>();
    private static List<Assignment> a2 = new ArrayList<>();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }


    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        d1 = new Dinnerevent(1L, "aaa", "location a", "dish a ", 1000000);
        d2 = new Dinnerevent(2L, "bbb", "location b", "dish b", 100000);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Dinnerevent.deleteAllRows").executeUpdate();
            em.persist(d1);
            em.persist(d2);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Getting so far");
        given().when().get("/dinnerevents").then().statusCode(200);
    }

    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/dinnerevents/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void testCreateDinnerevent() {
        Dinnerevent dinnerevent = new Dinnerevent(null, "Some eventname", "Some location", "Some dish", 100);

        given()
                .contentType(ContentType.JSON)
                .body(dinnerevent)
                .post("/dinnerevents/create/")
                .then()
                .statusCode(200)
                .log().body()
                .body("eventname", equalTo("Some eventname"))
                .body("location", equalTo("Some location"))
                .body("dish", equalTo("Some dish"))
                .body("price", equalTo(100));
    }

    @Test
    public void testGetAllDinnerevents() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/dinnerevents/all/")
                .then()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("$", hasSize(2))
                .log().body()
                .body("[0].eventname", equalTo("bbb"))
                .body("[1].eventname", equalTo("aaa"));

    }

    @Test
    public void testUpdateDinnerevent() {
        // Create a Dinnerevent object with updated values
        Dinnerevent updatedDinnerevent = new Dinnerevent(d1.getId(), "Updated eventname", "Updated location", "Updated dish", 200);

        given()
                .contentType(ContentType.JSON)
                .body(updatedDinnerevent)
                .put("/dinnerevents/update")
                .then()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .log().body()
                .body("eventname", equalTo("Updated eventname"))
                .body("location", equalTo("Updated location"))
                .body("dish", equalTo("Updated dish"))
                .body("price", equalTo(200));
    }



    @Test
    //virker ikke, statuscode 400?
    public void testDeleteDinnerevent() {
        Dinnerevent deletedDinnerevent = new Dinnerevent(1L, "aaa", "location a", "dish a ", 1000000);

        given()
                .contentType(ContentType.JSON)
                .body(deletedDinnerevent)
                .log().body()
                .delete("/dinnerevents/delete")
                .then()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .log().body()
                .body("id", equalTo(d1.getId().intValue()))
                .body("eventname", equalTo(d1.getEventname()))
                .body("location", equalTo(d1.getLocation()))
                .body("dish", equalTo(d1.getDish()))
                .body("price", equalTo(d1.getPrice()));
    }

}



