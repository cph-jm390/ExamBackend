package facades;

import dtos.TripDTO;
import entities.Guide;
import entities.Trip;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripFacadeTest {
    private static EntityManagerFactory emf;
    private static TripFacade facade;
    private static GuideFacade guideFacade;
    private static List<Trip> trips = new ArrayList<>();



    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = TripFacade.getFacade(emf);
        guideFacade = GuideFacade.getFacade(emf);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new User("test", "test"));
            em.getTransaction().commit();
        } catch (RollbackException e) {
            System.out.println("User already exists");
        } finally {
            em.close();
        }
    }

    @BeforeEach
    void setUp() {
        System.out.println("_______________________________________________________\nTESTING:\n");
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            em.createQuery("DELETE FROM Trip").executeUpdate();
            em.createQuery("DELETE FROM Trip").executeUpdate();
            em.createQuery("DELETE FROM Guide").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @Test
    void createTrip() {


        System.out.println("createTrip\n");
        EntityManager em = emf.createEntityManager();
        Guide guide = guideFacade.createGuide(new Guide("navn", "beskrivelse", "billede", "tester", "tester", trips));
        System.out.println("Guide was created: " + guide);
        Trip trip = new Trip(3L, "test", "test", "test", "test", "test","test", guide);
        Trip triptest = facade.createTrip(trip);
        System.out.println("Trip was created: " + triptest);
        assertNotNull(triptest);
    }
    @Test
    void getTripDTOById(){

        System.out.println("getTripById\n");
        EntityManager em = emf.createEntityManager();
        Guide guide = guideFacade.createGuide(new Guide("navn", "beskrivelse", "billede", "tester", "tester", trips));
        System.out.println("Guide was created: " + guide);
        Trip trip1 = facade.createTrip(new Trip(1L, "Dis one", "test", "test", "test", "test","test", guide));
        System.out.println("Trip 1 was created: " + trip1);
        Trip trip2 = facade.createTrip(new Trip(2L, "test2", "9/11", "test2", "test2", "test2","test2", guide));
        System.out.println("Trip 2 was created: " + trip2);
        System.out.println(trip2.getDate());
        TripDTO tripDTO = facade.getTripDTOById(2L);
        System.out.println(tripDTO.getId() + " " + tripDTO.getTrip_name() + " " + tripDTO.getDate() + " " + tripDTO.getTime() + " " + tripDTO.getDuration() + " " + tripDTO.getPackingList() + " " + tripDTO.getGuide_name());
        assertEquals("9/11", tripDTO.getDate());
    }
    @Test
    void getAll(){


        System.out.println("Getting all trips\n");
        Guide guide = guideFacade.createGuide(new Guide("navn", "beskrivelse", "billede", "tester", "tester", trips));
        System.out.println("Guide was created: " + guide);
        Trip trip1 = facade.createTrip(new Trip(1L, "test", "test", "test", "test", "test","test", guide));
        System.out.println("Trip 1 was created: " + trip1);
        Trip trip2 = facade.createTrip(new Trip(2L, "test2", "9/11", "test2", "test2", "test2","test2", guide));
        System.out.println("Trip 2 was created: " + trip2);
        assertEquals(2, facade.getAll().size());
    }
    }


