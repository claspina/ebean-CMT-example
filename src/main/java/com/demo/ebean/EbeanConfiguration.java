package com.demo.ebean;

import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.config.dbplatform.postgres.PostgresPlatform;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class EbeanConfiguration {

    @Resource(mappedName = "java:jboss/ebeanDS")
    private DataSource ds;

    @PostConstruct
    @SneakyThrows
    public void configEbeanServer() {

        ServerConfig config = new ServerConfig();
        config.setDataSource(ds);
        config.setName("db");
        config.setAutoCommitMode(false);
        config.setDatabasePlatform(new PostgresPlatform());
        config.setRegister(true);
        config.setDefaultServer(true);
        config.setTransactionRollbackOnChecked(true);
        config.addPackage(EbeanConfiguration.class.getPackage().getName());
        config.setUseJtaTransactionManager(true);

        config.setDdlGenerate(true);
        config.setDdlRun(true);

        // used in multiple threads... no prob. trx are  managed in a thread local storage.
        System.out.println("Init Ebean Server");
        EbeanServerFactory.create(config);
        System.out.println("Ebean Server created");

    }
}
