package com.demo.ebean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Carlos on 14/07/2017.
 */
@Path("/person")
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    @Inject
    private PersonBoundary personBoundary;

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create() {

        LOGGER.info("createFakePerson method");

        return Response.status(200).entity(personBoundary.createPeron()).build();

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response index() {

        LOGGER.info("index method");

        return Response.status(200).entity("index").build();

    }
}
