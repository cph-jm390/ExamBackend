package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.AssignmentDTO;
import dtos.DinnereventDTO;
import entities.Assignment;
import entities.Dinnerevent;
import entities.User;

import facades.AssignmentFacade;
import facades.DinnereventFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Path("assignments")
public class AssignmentResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final AssignmentFacade ASSIGNMENT_FACADE = AssignmentFacade.getFacade(EMF);
    private static final DinnereventFacade DINNEREVENT_FACADE = DinnereventFacade.getFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

   @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinnerevent(String json) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        long eventid = jsonObject.get("eventid").getAsLong();
       Dinnerevent dinnerevent =  DINNEREVENT_FACADE.getDinnereventById(eventid);
        Assignment assignment = GSON.fromJson(json, Assignment.class);
        assignment.setDinnerevent(dinnerevent);
        AssignmentDTO assignmentDTO = ASSIGNMENT_FACADE.createAssignment(assignment);

        return Response.ok(assignmentDTO).build();
    }
    @POST
    @Path("adduser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToAssignment(String json) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        int id= jsonObject.get("tripId").getAsInt();
        User user = UserFacade.getUserByUserEmail(username);
        Assignment assignment = ASSIGNMENT_FACADE.addUserToTrip(id,user);
        TripDTO tripDTO = new TripDTO(trip.getId(), trip.getTrip_name(), trip.getDate(), trip.getTime(), trip.getLocation(), trip.getDuration(), trip.getPackingList(), trip.getGuide().getGUIDE_NAME());
        return Response.ok(tripDTO).build();
    }
}