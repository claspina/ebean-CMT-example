package com.demo.ebean;

import com.demo.ebean.entity.Person;
import io.ebean.Ebean;
import lombok.extern.java.Log;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Log
@Path("/person")
@Stateless
public class PersonBoundary {

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create() {

        log.info("Creating fake person...");

        Person person = new Person();
        person.setAge(25);
        person.setName("David");
        person.setLastName("Hofmann "+ new Date());
        person.save();

        Person person2 = new Person();
        person2.setAge(25);
        person2.setName("Carlos ");
        person2.setLastName("Laspina "+ new Date());
        person2.save();
//        Ebean.getDefaultServer().
        log.info("2 new Persons created...");
        log.info("Equal: " +person.equals(person2));

        String resp = "Now we have " + Person.query().findCount() + " persons";
        return Response.status(200).entity(resp).build();
    }

}
