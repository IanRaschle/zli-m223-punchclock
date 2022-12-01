package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.processing.Generated;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;


@Path("/tags")
public class TagController {

    @Inject
    TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all tags.", description = "Returns a list of all tags.")
    public List<Tag> index() {
        return tagService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new tag.", description = "Creates a new tag and returns the newly added tag.")
    public Tag create(Tag entry) {
       return tagService.createEntry(entry);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes an tag", description = "Deletes the given tag and returns nothing")
    public void delete(@PathParam("id") long id) {
        tagService.deleteEntry(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "update an tag",description = "updates the tag with the given tag")
    public void update(Tag entry) {
        tagService.updateEntity(entry);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the selected tag.", description = "Get the selected tag by the given id.")
    @Path("/{id}")
    public Tag getEntry(@PathParam("id") String id) {
        return tagService.find(Long.parseLong(id));
    }

}
