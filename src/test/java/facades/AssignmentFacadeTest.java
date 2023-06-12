package facades;

import dtos.AssignmentDTO;
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

class AssignmentFacadeTest {
    private static EntityManagerFactory emf;
    private static AssignmentFacade facade;
    private static DinnereventFacade dinnereventFacade;
    private static List<Assignment> assignments = new ArrayList<>();


    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssignmentFacade.getFacade(emf);
        dinnereventFacade = DinnereventFacade.getFacade(emf);

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
        try {

            em.getTransaction().begin();
            em.createQuery("DELETE FROM Assignment ").executeUpdate();
            em.createQuery("DELETE FROM Assignment ").executeUpdate();
            em.createQuery("DELETE FROM Dinnerevent ").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createAssignment() {


        System.out.println("createAssignment\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent = dinnereventFacade.createDinnerevent(new Dinnerevent(null, "beskrivelse", "billede", "tester", 15, assignments));
        System.out.println("Dinnerevent was created: " + dinnerevent);
        Assignment assignment = new Assignment(null, "familyname", "date", "contactInfo", dinnerevent);
        AssignmentDTO assignmenttest = facade.createAssignment(assignment);
        System.out.println("Assignment was created: " + assignmenttest);
        assertNotNull(assignmenttest);
    }

    @Test
    void getAssignmentById() {
//only works alone
        System.out.println("getAssignmentById\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent = dinnereventFacade.createDinnerevent(new Dinnerevent(null, "beskrivelse", "billede", "tester", 15, assignments));
        System.out.println("dinnerevent was created: " + dinnerevent);
        Assignment assignment1 = new Assignment(null, "familyname1", "date1", "contactInfo1", dinnerevent);
        Assignment assignment2 = new Assignment(null, "familyname2", "date2", "contactInfo2", dinnerevent);
        AssignmentDTO adto1 = facade.createAssignment(assignment1);
        System.out.println("Assignment 1 was created: " + adto1);
        AssignmentDTO adto2 = facade.createAssignment(assignment2);
        System.out.println("Assignment 2 was created: " + adto2);
        Assignment foundassignment = facade.getAssignmentById(2L);
        assertEquals("familyname2", foundassignment.getFamilyname());
    }

    @Test
    void getAll() {


        System.out.println("getAllAssignments\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent = dinnereventFacade.createDinnerevent(new Dinnerevent(null, "beskrivelse", "billede", "tester", 15, assignments));
        System.out.println("dinnerevent was created: " + dinnerevent);
        Assignment assignment1 = new Assignment(null, "familyname1", "date1", "contactInfo1", dinnerevent);
        Assignment assignment2 = new Assignment(null, "familyname2", "date2", "contactInfo2", dinnerevent);
        AssignmentDTO adto1 = facade.createAssignment(assignment1);
        System.out.println("Assignment 1 was created: " + adto1);
        AssignmentDTO adto2 = facade.createAssignment(assignment2);
        System.out.println("Assignment 2 was created: " + adto2);
        assertEquals(2, facade.getAllAssignments().size());
    }

    @Test
    void addUserToAssignment() {
        System.out.println("addUserToAssignment\n");
        EntityManager em = emf.createEntityManager();
        Dinnerevent dinnerevent = dinnereventFacade.createDinnerevent(new Dinnerevent(null, "beskrivelse", "billede", "tester", 15, assignments));
        System.out.println("dinnerevent was created: " + dinnerevent);
        Assignment assignment1 = new Assignment(null, "familyname1", "date1", "contactInfo1", dinnerevent);
        AssignmentDTO adto1 = facade.createAssignment(assignment1);
        System.out.println("Assignment 1 was created: " + adto1);
        User user = new User("test", "test", "test", 1990, 1000);
        Assignment assignment = facade.addUserToAssignment(adto1.getId(), user);
        System.out.println("User was added to assignment: " + assignment);
        assertNotNull(assignment.getUsersList());
    }
}