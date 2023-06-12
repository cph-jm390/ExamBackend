package facades;

import dtos.AssignmentDTO;
import dtos.UserDTO;
import entities.Assignment;
import entities.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class  AssignmentFacade {
    static EntityManagerFactory emf;
    private static AssignmentFacade instance;

    public static AssignmentFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssignmentFacade();
        }
        return instance;
    }
    public static Assignment getAssignmentById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Assignment assignment=em.find(Assignment.class, id);
            System.out.println("getAssignmentById nåede til return statement med objekt: " + assignment + " med navn: " + assignment.getFamilyname());
            return em.find(Assignment.class, id);
        } finally {
            em.close();
        }
    }
    public AssignmentDTO getAssignmentById(int id) {
        return null;
    }

    public AssignmentDTO createAssignment(Assignment assignment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(assignment);
            em.getTransaction().commit();

            return new AssignmentDTO(assignment.getId(), assignment.getFamilyname(), assignment.getDate(), assignment.getContactInfo(), assignment.getDinnerevent().getEventname());
        } finally {
            em.close();
        }
    }
    public static Assignment addUserToTrip(long id, User user) {
        EntityManager em = emf.createEntityManager();
        Assignment assignment = getAssignmentById(id);
        assignment.addUser(user);
        System.out.println(user + " added to " + assignment);


        try {
            em.getTransaction().begin();
            System.out.println("transaction started");
            // Associate the users with the trip
            em.merge(user);
            System.out.println("user: " + user + " merged");
            em.merge(assignment);
            System.out.println("assignment: " + assignment + " merged");
            em.getTransaction().commit();
            return assignment;
        } finally {
            em.close();
        }
    }

    public List<AssignmentDTO> getAllAssignments() {
       return null;
    }
}
