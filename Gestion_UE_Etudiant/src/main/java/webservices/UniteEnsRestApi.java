package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/ue")
public class UniteEnsRestApi {

    private static final UniteEnseignementBusiness helper =
            new UniteEnseignementBusiness();

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
    @Path("/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);

        if (ue != null) {
            return Response.ok(ue).build();
        }

        return Response.status(404)
                .entity("UE introuvable")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }


    @Path("/recherche/domaine/{domaine}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByDomaine(
            @PathParam("domaine") String domaine) {

        return Response.ok(
                helper.getUEByDomaine(domaine)
        ).build();
    }

    @Path("/recherche/semestre/{semestre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(
            @PathParam("semestre") int semestre) {

        return Response.ok(
                helper.getUEBySemestre(semestre)
        ).build();
    }

    @Path("/{code}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(
            @PathParam("code") int code,
            UniteEnseignement updatedUE) {

        boolean result =
                helper.updateUniteEnseignement(code, updatedUE);

        if (result) {
            return Response.ok("Modification reussie").build();
        }

        return Response.status(404)
                .entity("UE introuvable")
                .build();
    }

    @Path("/{code}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {

        boolean result = helper.deleteUniteEnseignement(code);

        if (result) {
            return Response.ok("Suppression reussie").build();
        }

        return Response.status(404)
                .entity("UE introuvable")
                .build();
    }








}
