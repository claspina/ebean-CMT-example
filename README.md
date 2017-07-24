# ebean-CMT-example
A simple POC for Ebean with Container-Managed Transactions


Configuring wildfly datasource
------------------------------
copy postgresql-9.4.1211.jar to deploy directory in deploy directory (if postgres driver not configured yet)
inside jboss-cli.sh
data-source add --jndi-name=java:jboss/datasources/ebeanDS --name=ebeanDS --connection-url=jdbc:postgresql:ebeantest --driver-name=postgresql-9.4.1211.jar  --user-name=s2user --password=s2user
/subsystem=datasources/data-source=ebeanDS/:write-attribute(name=max-pool-size, value=3)
 
 
Configuring Payara datasource ... ?
------------------------------------
look at microdomain.xml