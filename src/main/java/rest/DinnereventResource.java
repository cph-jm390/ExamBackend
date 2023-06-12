package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DinnereventDTO;
import entities.Dinnerevent;
import entities.User;

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
@Path("dinnerevents")
public class DinnereventResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
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
        Dinnerevent dinnerevent = GSON.fromJson(json, Dinnerevent.class);
        DINNEREVENT_FACADE.createDinnerevent(dinnerevent);
        return Response.ok(dinnerevent).build();
    }


    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGuides() {
        List<DinnereventDTO> dinnereventDTOs = DINNEREVENT_FACADE.getAllDinnerevents();
        for (DinnereventDTO dinnereventDTO : dinnereventDTOs) {
            System.out.println(dinnereventDTO.getEventname());
        }
        return Response.ok(dinnereventDTOs).build();
    }
    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDinnerevent(String json) throws IOException {
        Dinnerevent dinnerevent = GSON.fromJson(json, Dinnerevent.class);
        DinnereventDTO dinnereventDTO = DINNEREVENT_FACADE.updateDinnerevent(dinnerevent);
        return Response.ok(dinnereventDTO).build();
    }
    /*@GET
    @Path("specific")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificGuide(String json){
        GuideDTO guideDTO = GUIDE_FACADE.getGuideDTOByName(json);
        System.out.println(guideDTO.getGUIDE_NAME());
        System.out.println(guideDTO.getGender());
        return Response.ok(guideDTO).build();
    }
*/

}