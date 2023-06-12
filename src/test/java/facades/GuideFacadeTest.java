package facades;

import dtos.GuideDTO;
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

class GuideFacadeTest {
    private static EntityManagerFactory emf;
    private static GuideFacade facade;

    private static List<Trip> trips = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = GuideFacade.getFacade(emf);
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
            em.createQuery("DELETE FROM Guide").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @Test
    void createGuide() {


        System.out.println("createTrip\n");
        EntityManager em = emf.createEntityManager();
        Guide guide = facade.createGuide(new Guide("navn", "beskrivelse", "billede", "tester", "tester", trips));
        System.out.println("Guide was created: " + guide);
        assertNotNull(guide);
    }

    @Test
    void getGuideByName() {
        System.out.println("getGuideByName\n");
        EntityManager em = emf.createEntityManager();
        Guide guide = facade.createGuide(new Guide("navn", "beskrivelse", "billede", "tester", "tester", trips));
        System.out.println("Guide was created: " + guide);
        assertNotNull(guide);
        Guide guide1 = facade.getGuideByName("navn");
        System.out.println("Guide was found: " + guide1);
        assertEquals("navn", guide1.getGUIDE_NAME());
    }
    @Test
    void getGuideDTOByName() {
        System.out.println("getGuideByName\n");
        EntityManager em = emf.createEntityManager();
        Guide guide = facade.createGuide(new Guide("navn", "beskrivelse", "billede", "tester", "tester", trips));
        System.out.println("Guide was created: " + guide);
        assertNotNull(guide);
        GuideDTO guideDTO = facade.getGuideDTOByName("navn");
        System.out.println("Guide was found: " + guideDTO);
        assertEquals("navn", guideDTO.getGUIDE_NAME());
    }
    @Test
    void getAllGuides(){
        System.out.println("getAllGuides\n");
        EntityManager em = emf.createEntityManager();
        Guide guide1 = facade.createGuide(new Guide("Din Mor", "beskrivelse", "billede", "tester", "tester", trips));
        Guide guide2 = facade.createGuide(new Guide("Din Far", "beskrivelse", "billede", "tester", "tester", trips));

        System.out.println("Guide was created: " + guide1);
        System.out.println("Guide was created: " + guide2);
        assertNotNull(guide1);
        assertNotNull(guide2);
        List<GuideDTO> guides = facade.getAllGuides();
        System.out.println("Guides was found: " + guides.get(0).getGUIDE_NAME() + " and " + guides.get(1).getGUIDE_NAME());
        assertEquals(2, guides.size());
    }
}