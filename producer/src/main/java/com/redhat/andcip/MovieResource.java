package com.redhat.andcip;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/movies")
public class MovieResource {

    @Inject
    @Channel("movies")
    Emitter<Movie> emitter;

    @POST
    @Path("/{id}")
    public void createRequest(@PathParam("id") String id) {

        Movie movie = Movie.newBuilder().setTitle("Test").setYear(2023).build();
/*        OutgoingCloudEventMetadata<Object> cloudEventMetadata = OutgoingCloudEventMetadata.builder()
                .withId(id)
                .withSpecVersion("1.0")
                .withSource(URI.create("http://localhost:8080/movies"))
                .withType("movie")
                .build();
        Message<Movie> message = Message.of(movie); */
        emitter.send(movie);

    }
}
