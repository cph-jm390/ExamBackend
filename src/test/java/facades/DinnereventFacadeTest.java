package facades;


import dtos.DinnereventDTO;
import entities.Dinnerevent;
import entities.Assignment;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DinnereventFacadeTest {
    private static EntityManagerFactory emf;
    private static DinnereventFacade facade;


    private static List<Assignment> assignments = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = DinnereventFacade.getFacade(emf);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new User("test", "test", "test", 1990, 1000));
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
        Dinnerevent dinnerevent;
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Dinnerevent").executeUpdate();
            em.persist(dinnerevent = new Dinnerevent(2L, "testNavn1", "testLocation1", "lasagne1", 15));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createDinnerevent() {


        System.out.println("createTrip\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent = facade.createDinnerevent(new Dinnerevent(2L, "testNavn2", "testLocation2", "lasagne", 15));
        System.out.println("Dinnerevent was created: " + dinnerevent.getEventname());
        assertNotNull(dinnerevent);
    }

    @Test
    void getDinnereventById() {
        //Virker kun alene i testen
        System.out.println("getDinnereventById\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent = facade.createDinnerevent(new Dinnerevent(2L, "testNavn2", "testLocation2", "lasagne2", 15));
        assertNotNull(dinnerevent);
        System.out.println("dinnerevent created successfully");
        Dinnerevent dinnereventFound = facade.getDinnereventById(1L);
        System.out.println("Dinnerevent was found: " + dinnereventFound);
        assertEquals("testNavn1", dinnereventFound.getEventname());
    }

    @Test
    void getAll() {
        //Virker kun alene i testen
        System.out.println("getAll\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent1 = facade.createDinnerevent(new Dinnerevent(2L, "testNavn2", "testLocation2", "lasagne", 15));
        assertNotNull(dinnerevent1);
        System.out.println("dinnerevent1 created successfully");
        Dinnerevent dinnerevent2 = facade.createDinnerevent(new Dinnerevent(3L, "testNavn3", "testLocation3", "lasagne", 15));
        assertNotNull(dinnerevent2);
        Dinnerevent dinnerevent3 = facade.getDinnereventById(2L);
        System.out.println("dinnerevent2 created successfully");
        List<DinnereventDTO> dinnereventList = facade.getAllDinnerevents();
        for (DinnereventDTO dinnereventDTO : dinnereventList) {
            System.out.println(dinnereventDTO.getEventname());
        }
        assertEquals(3, dinnereventList.size());
    }

    @Test
    void updateDinnerevent() {
        System.out.println("updateDinnerevent\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent1 = facade.createDinnerevent(new Dinnerevent(null, "testNavn2", "testLocation2", "lasagne", 15));
        dinnerevent1.setDish("pizza");
        DinnereventDTO dinnerevent2 = facade.updateDinnerevent(dinnerevent1);
        assertEquals("pizza", dinnerevent2.getDish());
    }

    @Test
    void deleteDinnerevent() {
        System.out.println("deleteDinnerevent\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent1 = facade.createDinnerevent(new Dinnerevent(null, "testNavn2", "testLocation2", "lasagne", 15));
        facade.deleteDinnerevent(dinnerevent1.getId());
        List<DinnereventDTO> dinnereventList = facade.getAllDinnerevents();
        assertEquals(1, dinnereventList.size());
    }
}
