package webservices;

import entities.Module;
import entities.Module.TypeModule;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleRestApi {

    private static final ModuleBusiness helper =
            new ModuleBusiness();

    private static final UniteEnseignementBusiness ueHelper =
            new UniteEnseignementBusiness();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {

        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {

        if (helper.addModule(module)) {
            return Response.status(201)
                    .entity("Module ajoute avec succes")
                    .build();
        }

        return Response.status(400)
                .entity("Erreur lors de l'ajout")
                .build();
    }

    @Path("/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(
            @PathParam("matricule") String matricule) {

        Module module = helper.getModuleByMatricule(matricule);

        if (module != null) {
            return Response.status(200).entity(module).build();
        }

        return Response.status(404)
                .entity("Module introuvable")
                .build();
    }

    @Path("/recherche/type/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(
            @PathParam("type") String type) {

        try {
            TypeModule typeModule =
                    TypeModule.valueOf(type.toUpperCase());

            return Response.status(200)
                    .entity(helper.getModulesByType(typeModule))
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(400)
                    .entity("Type de module invalide")
                    .build();
        }
    }

    @Path("/recherche/ue/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(
            @PathParam("code") int code) {

        UniteEnseignement ue = ueHelper.getUEByCode(code);

        if (ue == null) {
            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }

        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }

    @Path("/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(
            @PathParam("matricule") String matricule,
            Module module) {

        boolean updated = helper.updateModule(matricule, module);

        if (updated) {
            return Response.status(200)
                    .entity("Module modifie avec succes")
                    .build();
        }

        return Response.status(404)
                .entity("Module introuvable")
                .build();
    }

    @Path("/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(
            @PathParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("Module supprime avec succes")
                    .build();
        }

        return Response.status(404)
                .entity("Module introuvable")
                .build();
    }

}