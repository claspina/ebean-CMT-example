package com.demo;

import com.demo.ebean.entity.Person;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.dbmigration.DbMigration;
import lombok.SneakyThrows;
import org.postgresql.ds.PGPoolingDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DdlGenerator {

    @SneakyThrows
    public static void main(String[] args ) {

//        System.setProperty("ddl.migration.version", "1.0");// optional & auto-increment
        String messageIteration = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        System.setProperty("ddl.migration.name", messageIteration);

        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("ebeantest");
        source.setUser("s2user");
        source.setPassword("s2user");

        ServerConfig sc = new ServerConfig();
        sc.setDataSource(source);
        sc.addPackage(Person.class.getPackage().getName());
        EbeanServer es = EbeanServerFactory.create(sc);

        DbMigration dbMigration = new DbMigration();
        dbMigration.setServer(es);
        dbMigration.generateMigration(); // generate the migration ddl and xml  with EbeanServer in "offline" mode
    }
}
