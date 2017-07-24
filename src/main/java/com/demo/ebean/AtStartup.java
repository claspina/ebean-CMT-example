package com.demo.ebean;

import com.demo.ebean.entity.Person;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ContainerConfig;
import io.ebean.config.ServerConfig;
import io.ebean.dbmigration.MigrationConfig;
import io.ebean.dbmigration.MigrationRunner;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

@Log
@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN) // TODO por que esto aca ?, sin esto el contenedor quiere comitear al final algo que ya se comiteo... ?
public class AtStartup {

    @Resource(mappedName = "java:jboss/datasources/ebeanDS")
    private DataSource ds;

    @PostConstruct
    @SneakyThrows
    // FIXME this code is in an undefined transaction state by the SPEC, but we are using BTM
    // FIXME this should be calling another EJB to execute the code in a transaction scope
    //
    public void configEbeanServer() {

        new MigrationRunner(new MigrationConfig()).run(ds.getConnection()); // begin/commits transaction for the migration...
        log.info("Migration Completed");

        // the following code doesn't need a transaction...
        ServerConfig config = new ServerConfig();
//        config.
//        ContainerConfig cc = new ContainerConfig();
//        config.setContainerConfig(cc)//;DataSource(ds);
        config.addPackage(Person.class.getPackage().getName());
        config.setUseJtaTransactionManager(true); // false by default
        EbeanServerFactory.create(config); // used in multiple threads... no prob. trx are  managed in a thread local storage.
        log.info("Ebean Server registered");
    }
}
