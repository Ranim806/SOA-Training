package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/ue")
public class UniteEnsRestApi {

    UniteEnseignementBusiness helper=new UniteEnseignementBusiness();

    //getlistUE
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUe(){
        return Response.status(200).entity(helper.getListeUE()).build();

    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue){
        if(helper.addUniteEnseignement(ue)){
            return Response.status(201).entity("Success").build();
        }else{
            return Response.status(400).entity("Erreur").build();
        }
    }





}
