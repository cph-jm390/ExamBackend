package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.GuideDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Trip;
import entities.User;
import facades.GuideFacade;
import facades.TripFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("trips")
public class TripResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final GuideFacade GUIDE_FACADE = GuideFacade.getFacade(EMF);
    private static final TripFacade TRIP_FACADE = TripFacade.getFacade(EMF);

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrip(String json) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        String guide_name = jsonObject.get("guide_name").getAsString();

        // Retrieve the existing Guide entity
        Guide guide = GUIDE_FACADE.getGuideByName(guide_name);

        // Create the trip and associate it with the guide
        Trip trip = GSON.fromJson(json, Trip.class);
        trip.setGuide(guide);

        // Persist the trip entity
        TRIP_FACADE.createTrip(trip);


        TripDTO tripDTO = new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());

        return Response.ok(tripDTO).build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrips(){
    List<TripDTO> tripDTOs = TRIP_FACADE.getAll();

    System.out.println(tripDTOs.get(0).getTrip_name());

          return Response.ok(tripDTOs).build();
}
    @POST
    @Path("assign")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignUserToTrip(String json) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        String user_name = jsonObject.get("username").getAsString();
        int id= jsonObject.get("tripId").getAsInt();
        User user = UserFacade.getUserByUsername(user_name);
        Trip trip = TRIP_FACADE.assignUserToTrip(id,user);
        TripDTO tripDTO = new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());
        return Response.ok(tripDTO).build();
    }
@PUT
    @Path ("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTrip(String json){
    JsonParser parser = new JsonParser();
    JsonObject jsonObject = parser.parse(json).getAsJsonObject();
    String guide_name = jsonObject.get("guide_name").getAsString();

    // Retrieve the existing Guide entity
    Guide guide = GUIDE_FACADE.getGuideByName(guide_name);

    // Create the trip and associate it with the guide
    Trip trip = GSON.fromJson(json, Trip.class);
    trip.setGuide(guide);

    TripDTO tripDTO = TRIP_FACADE.updateTrip(trip);
    return Response.ok(tripDTO).build();
    }
    @DELETE
    @Path("removeUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUserFromTrip(String json){
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
            String userName = jsonObject.get("userName").getAsString();
            int tripId = jsonObject.get("tripId").getAsInt();
            TripDTO tripDTO= TRIP_FACADE.removeUserFromTrip(tripId,userName);
            return Response.ok(tripDTO).build();


    }

}



