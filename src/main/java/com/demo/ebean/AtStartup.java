package com.demo.ebean;

import com.demo.ebean.entity.Person;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.TransactionCallback;
import io.ebean.config.ContainerConfig;
import io.ebean.config.CurrentUserProvider;
import io.ebean.config.ServerConfig;
import io.ebean.dbmigration.MigrationConfig;
import io.ebean.dbmigration.MigrationRunner;
import io.ebean.event.ServerConfigStartup;
import io.ebeaninternal.server.core.DefaultServer;
import io.ebeaninternal.server.transaction.JtaTransaction;
import io.ebeaninternal.util.JdbcClose;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.avaje.datasource.DataSourceConfig;

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
@TransactionManagement(TransactionManagementType.BEAN) // this is so that the container won't complain when ebean migration tries to commit
public class AtStartup {

    @Resource(mappedName = "java:jboss/datasources/ebeanDS")
    private DataSource ds;

    @PostConstruct
    public void configEbeanServer() {
        // runs migration scripts
        new MigrationRunner(new MigrationConfig()).run(ds); // begin/commits transaction for the migration...

        // Configures ebean server
        ServerConfig config = new ServerConfig();
        config.setDataSource(ds);
        config.addPackage(Person.class.getPackage().getName());
        config.setUseJtaTransactionManager(true); // false by default
        EbeanServerFactory.create(config);// used in multiple threads... no prob. trx are  managed in a thread local storage.

    }
}
