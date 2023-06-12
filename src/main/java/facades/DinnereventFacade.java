package facades;

import dtos.AssignmentDTO;
import dtos.DinnereventDTO;
import entities.Dinnerevent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DinnereventFacade {
    static EntityManagerFactory emf;
    private static DinnereventFacade instance;

    public static DinnereventFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DinnereventFacade();
        }
        return instance;
    }

    public Dinnerevent createDinnerevent(Dinnerevent dinnerevent) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dinnerevent);
            em.getTransaction().commit();
            return dinnerevent;
        } finally {
            em.close();
        }
    }

    public static Dinnerevent getDinnereventById(long id) {
        System.out.println("getDinnereventById startet");
        EntityManager em = emf.createEntityManager();
        try {
            Dinnerevent dinnerevent = em.find(Dinnerevent.class, id);
            return dinnerevent;
        } finally {
            em.close();
        }
    }

    public List<DinnereventDTO> getAllDinnerevents() {
        System.out.println("getAllDinnerevents startet");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Dinnerevent> query = em.createQuery("SELECT d FROM Dinnerevent d", Dinnerevent.class);
            List<Dinnerevent> dinnerevents = query.getResultList();

            List<DinnereventDTO> dinnereventDTOs = new ArrayList<>();
            for (Dinnerevent dinnerevent : dinnerevents) {

                DinnereventDTO dinnereventDTO = new DinnereventDTO(dinnerevent.getId(), dinnerevent.getEventname(), dinnerevent.getLocation(), dinnerevent.getDish(), dinnerevent.getPrice(), null);
                dinnereventDTOs.add(dinnereventDTO);
            }
            return dinnereventDTOs;
        } finally {
            em.close();
        }
    }

    public static DinnereventDTO updateDinnerevent(Dinnerevent dinnerevent) {
        System.out.println("updateDinnerevent startet");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(dinnerevent);
            em.getTransaction().commit();
            return new DinnereventDTO(dinnerevent.getId(), dinnerevent.getEventname(), dinnerevent.getLocation(), dinnerevent.getDish(), dinnerevent.getPrice(), null);
        } finally {
            em.close();
        }


    }

    public static DinnereventDTO deleteDinnerevent(long id) {
        System.out.println("deleteDinnerevent startet");
        EntityManager em = emf.createEntityManager();
        try {
            Dinnerevent dinnerevent = em.find(Dinnerevent.class, id);
            em.getTransaction().begin();
            em.remove(dinnerevent);
            em.getTransaction().commit();
            return new DinnereventDTO(dinnerevent.getId(), dinnerevent.getEventname(), dinnerevent.getLocation(), dinnerevent.getDish(), dinnerevent.getPrice(), null);
        } finally {
            em.close();
        }
    }
}
