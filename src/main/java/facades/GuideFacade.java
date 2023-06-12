package facades;

import dtos.GuideDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Trip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class GuideFacade {
    static EntityManagerFactory emf;
    private static GuideFacade instance;

    public static GuideFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }

    public static Guide createGuide(Guide guide) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
            return guide;
        } finally {
            em.close();
        }
    }

    public static Guide getGuideByName(String guide_name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Guide.class, guide_name);
        } finally {
            em.close();
        }
    }
    public static GuideDTO getGuideDTOByName(String guide_name) {
        EntityManager em = emf.createEntityManager();
        try {
            Guide guide = em.find(Guide.class, guide_name);
            return new GuideDTO(guide.getGUIDE_NAME(), guide.getGender(), guide.getBirthyear(), guide.getProfile(), guide.getImageUrl(), null);
        } finally {
            em.close();
        }
    }

    public static List<GuideDTO> getAllGuides() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Guide> query = em.createQuery("SELECT g FROM Guide g", Guide.class);
            List<Guide> guides = query.getResultList();

            List<GuideDTO> guideDTOs = new ArrayList<>();
            for (Guide guide : guides) {

                GuideDTO guideDTO = new GuideDTO(guide.getGUIDE_NAME(), guide.getGender(), guide.getBirthyear(), guide.getProfile(), guide.getImageUrl(), null);
                guideDTOs.add(guideDTO);
            }
            System.out.println(guideDTOs.get(0).getGUIDE_NAME() + " " + guideDTOs.get(1).getGUIDE_NAME());
            return guideDTOs;
        } finally {
            em.close();
        }
    }
}

