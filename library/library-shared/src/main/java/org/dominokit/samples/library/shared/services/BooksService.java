package org.dominokit.samples.library.shared.services;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;
import org.dominokit.samples.library.shared.model.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestFactory
@Path("/books")
public interface BooksService {
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Book> list();

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Book create(Book book);

    @Path("/{title}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Book update(@PathParam("title") String title,  Book book);

    @Path("/{title}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Book delete(@PathParam("title") String title);

    @Path("/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Book get(@PathParam("title") String title);
}
