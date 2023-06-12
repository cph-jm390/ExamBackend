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
    private static final UserFacade USER_FACADE = UserFacade.getFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAssignments() {
        List<AssignmentDTO> assignmentDTOs = ASSIGNMENT_FACADE.getAllAssignments();
        for (AssignmentDTO adto : assignmentDTOs) {
            System.out.println(adto.getFamilyname());
        }
        return Response.ok(assignmentDTOs).build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinnerevent(String json) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        //tager eventid fra json og laver det om til long
        long eventid = jsonObject.get("eventid").getAsLong();
        Dinnerevent dinnerevent = DINNEREVENT_FACADE.getDinnereventById(eventid);
        //tager assignment fra jsonobject og laver det om til en assignment
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
        //tager userInput fra json og laver det om til en string
        String userInput = jsonObject.get("userInput").getAsString();
        //tager id fra json og laver det om til long

        long id = jsonObject.get("id").getAsLong();
        User user = USER_FACADE.getUserByUserEmail(userInput);
        Assignment assignment = ASSIGNMENT_FACADE.addUserToAssignment(id, user);
        AssignmentDTO assignmentDTO = new AssignmentDTO(assignment.getId(), assignment.getFamilyname(), assignment.getDate(), assignment.getContactInfo(), assignment.getDinnerevent().getEventname());
        return Response.ok(assignmentDTO).build();
    }
}