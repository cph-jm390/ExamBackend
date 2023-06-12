package facades;

import dtos.GuideDTO;
import dtos.TripDTO;
import dtos.UserDTO;
import entities.Guide;
import entities.Trip;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TripFacade {
    static EntityManagerFactory emf;
    private static TripFacade instance;
    private static UserFacade userFacade;

    public static TripFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }

    public static Trip createTrip(Trip trip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
            return trip;
        } finally {
            em.close();
        }

    }


    public static Trip getTripById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Trip trip=em.find(Trip.class, id);

            return em.find(Trip.class, id);
        } finally {
            em.close();
        }
    }

    public static TripDTO getTripDTOById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Trip trip = em.find(Trip.class, id);
            return new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());
        } finally {
            em.close();
        }
    }

    public static List<TripDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
            List<Trip> trips = query.getResultList();

            List<TripDTO> tripDTOs = new ArrayList<>();

            for (Trip trip : trips) {
                List<UserDTO> userDTOs = new ArrayList<>();
                TripDTO tripDTO = new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());
                if(trip.getUsersList() != null) {
                    for (User user : trip.getUsersList()) {
                        UserDTO userDTO = new UserDTO(user.getUserName(), user.getUserPass());
                        userDTOs.add(userDTO);

                    }
                }
                tripDTO.setUsersList(userDTOs);
                tripDTOs.add(tripDTO);

            }

            return tripDTOs;
        } finally {
            em.close();
        }
    }

    public static Trip assignUserToTrip(int id, User user) {
        EntityManager em = emf.createEntityManager();
        Trip trip = getTripById((long) id);
        trip.addUser(user);
        System.out.println(user + " added to " + trip);


        try {
            em.getTransaction().begin();
            System.out.println("transaction started");
            // Associate the users with the trip
            em.merge(user);
            System.out.println("user: " + user + " merged");
            em.merge(trip);
            System.out.println("trip: " + trip + " merged");
            em.getTransaction().commit();
            return trip;
        } finally {
            em.close();
        }
    }

    public static TripDTO updateTrip(Trip trip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(trip);
            em.getTransaction().commit();
            return new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());

        } finally {
            em.close();
        }

    }

   /* public static TripDTO removeUserFromTrip(int id, String user_name) {
        EntityManager em = emf.createEntityManager();
        try {
            Trip trip = getTripById((long) id);
            User user = userFacade.getUserByUsername(user_name);
            for (User user1 : trip.getUsersList()) {
                if (user1.getUserName().equals(user_name)) {
                    System.out.println("pre remove: " + trip.getUsersList());
                    trip.getUsersList().remove(user1);
                    System.out.println("after remove: " + trip.getUsersList());
                    break;
                }
            }
            for (Trip trip1 : user.getTripList()) {
                if (trip1.getId() == id) {
                    System.out.println("pre remove: " + user.getTripList());
                    user.getTripList().remove(trip1);
                    System.out.println("after remove: " + user.getTripList());
                    break;
                }
            }

            em.getTransaction().begin();
            em.merge(user);
            em.merge(trip);
            System.out.println("should be merged now");
            em.getTransaction().commit();
            return new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());

        } finally {
            em.close();
        }
    }*/
   public static TripDTO removeUserFromTrip(int id, String user_name) {
       EntityManager em = emf.createEntityManager();
       try {
           Trip trip = getTripById((long) id);
           User user = userFacade.getUserByUsername(user_name);

           if (trip.getUsersList().removeIf(u -> u.getUserName().equals(user_name))) {
               user.getTripList().remove(trip);

               em.getTransaction().begin();

               // Remove the relationship in the join table
               trip.getUsersList().remove(user);

               em.getTransaction().commit();

               return new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());
           } else {
               // User was not found in the trip
               return null;
           }
       } finally {
           em.close();
       }
   }


}
