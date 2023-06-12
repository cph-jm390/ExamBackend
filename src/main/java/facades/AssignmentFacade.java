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

public class AssignmentFacade {
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
        System.out.println("getAssignmentById startet");
        EntityManager em = emf.createEntityManager();
        try {
            Assignment assignment = em.find(Assignment.class, id);
            return em.find(Assignment.class, id);
        } finally {
            em.close();
        }
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

    public static Assignment addUserToAssignment(long id, User user) {
        System.out.println("start of addUserToAssignment");
        EntityManager em = emf.createEntityManager();
        Assignment assignment = getAssignmentById(id);
        List<User> users = new ArrayList<>();
        assignment.setUsersList(users);
        System.out.println("Assignment successfully added list of users");
        assignment.addUser(user);
        System.out.println(user + " added to " + assignment);
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.merge(assignment);
            em.getTransaction().commit();
            return assignment;
        } finally {
            em.close();
        }
    }

    public List<AssignmentDTO> getAllAssignments() {
        System.out.println("start of getAllAssignments");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Assignment> query = em.createQuery("SELECT a FROM Assignment a", Assignment.class);
            List<Assignment> assignments = query.getResultList();
            /*System.out.println("List of assignments gotten: " + assignments.size());
            System.out.println("box in data from assignments");
            System.out.println("Familyname "+ assignments.get(0).getFamilyname());
            System.out.println("Date "+ assignments.get(0).getDate());
            System.out.println("Contactinfo "+ assignments.get(0).getContactInfo());
            System.out.println("Dinnerevent "+ assignments.get(0).getDinnerevent().getEventname());
            System.out.println("box in data from assignments");*/
            List<AssignmentDTO> assignmentDTOs = new ArrayList<>();
            for (Assignment a : assignments) {
                List<UserDTO> userDTOs = new ArrayList<>();
                AssignmentDTO assignmentDTO = new AssignmentDTO(a.getId(), a.getFamilyname(), a.getDate(), a.getContactInfo(), a.getDinnerevent().getEventname());
                /*System.out.println("List of assignments gotten: " + assignments.size());
                System.out.println("box in data from assignmentDTO");
                System.out.println("Familyname "+ assignmentDTO.getFamilyname());
                System.out.println("Date "+ assignmentDTO.getDate());
                System.out.println("Contactinfo "+ assignmentDTO.getContactinfo());
                System.out.println("Dinnerevent "+ assignmentDTO.getEventname());
                System.out.println("box in data from assignmentDTO");*/
                //nedenstående udkommentering virker ikke for some reason.
                /*
                if(a.getUsersList() != null) {
                    for (User user : a.getUsersList()) {
                        UserDTO userDTO = new UserDTO(user.getUserEmail(), user.getUserPass());
                        userDTOs.add(userDTO);

                    }
                }
                assignmentDTO.setUsersList(userDTOs);*/
                assignmentDTOs.add(assignmentDTO);

            }
            System.out.println("List of assignments gotten with size: " + assignments.size() + ". Returning");
            return assignmentDTOs;
        } finally {
            em.close();

        }
    }
}
