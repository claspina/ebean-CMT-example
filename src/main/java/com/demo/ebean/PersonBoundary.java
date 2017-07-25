package com.demo.ebean;

import com.demo.ebean.entity.Person;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.TransactionCallback;
import io.ebeaninternal.api.SpiTransaction;
import io.ebeaninternal.server.core.DefaultServer;
import io.ebeaninternal.server.transaction.JtaTransaction;
import io.ebeaninternal.util.JdbcClose;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@Log
@Path("/person")
@Stateless

public class PersonBoundary {

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @SneakyThrows
    public Response create() {

        // TODO un comment this to make ebean release the connection after the JTA transaction is completed.
        /*
        DefaultServer ds = (DefaultServer) Ebean.getDefaultServer();
        JtaTransaction cst = (JtaTransaction) ds.getCurrentServerTransaction();
        TransactionSynchronizationRegistry tsr = (TransactionSynchronizationRegistry) new InitialContext().lookup("java:comp/TransactionSynchronizationRegistry");
        tsr.registerInterposedSynchronization( new Synchronization() {
            public void beforeCompletion() { }
            public void afterCompletion(int status) {
                try { cst.getInternalConnection().close(); } catch (SQLException ignore) { }
            }
        });
        */

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



        log.info("2 new Persons created...");
        log.info("Equal: " +person.equals(person2));

        String resp = "Now we have " + Person.query().findCount() + " persons";
        return Response.status(200).entity(resp).build();
    }

}
