package facades;

import dtos.AssignmentDTO;
import entities.Assignment;

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

    public AssignmentDTO getAssignmentById(int id) {
        return null;
    }

    public List<AssignmentDTO> getAllAssignments() {
       return null;
    }
}
