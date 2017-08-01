# ebean-CMT-example
A simple POC for Ebean with Container-Managed Transactions


Configuring wildfly datasource
------------------------------
```
inside jboss-cli.sh

if not done previously, add postgres module
module add --name=org.postgresql --slot=main --resources=/Users/hdavid/Downloads/postgresql-42.1.3.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)

if not done previously, add datasource...
data-source add --jndi-name=java:jboss/datasources/ebeanDS --name=EbeanDS --connection-url=jdbc:postgresql:ebeantest --driver-name=postgres  --user-name=s2user --password=s2user
/subsystem=datasources/data-source=EbeanDS/:write-attribute(name=max-pool-size, value=7)
```
 