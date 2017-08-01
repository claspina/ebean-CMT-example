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

  ##Wildfly JDBC security Example
  
  **Define a JDBC security Domain** 
  
   In the standalone.xml (inside <security-domains>) place something like this
  
 ```
                <security-domain name="testjdbc-policy" cache-type="default">
                     <authentication>
                         <login-module code="Database" flag="required">
                             <module-option name="dsJndiName" value="java:/jdbc/demoDS"/>
                             <module-option name="principalsQuery" value="SELECT pass FROM users WHERE username=?"/>
                             <module-option name="rolesQuery" value="SELECT rolename,'Roles' from user_role WHERE username=?"/>
                             <module-option name="hashAlgorithm" value="MD5"/> <!--MD5 , SHA-256 ,SHA-512 -->
                             <module-option name="hashEncoding" value="base64"/>  <!--base64, hex ,rfc2617-->
                             <module-option name="unauthenticatedIdentity" value="guest"/>
                         </login-module>
                     </authentication>
                 </security-domain>
 ```
 **Specify which security domain use**
 
 Set the security-domain in the WEB-INF\jboss-web.xml file
  ```
 <jboss-web>
 	<security-domain>testjdbc-policy</security-domain> 
 	<disable-audit>false</disable-audit>  
 </jboss-web>
  ```
  The security-domain must match the security-domain "name" defined in the standalone.xml
  
  **Add the constraint in your application**
  
   ```
    ...
    <login-config>
      <auth-method>BASIC</auth-method>
      <realm-name>Basic Auth</realm-name>
    </login-config>
    ...
    <security-constraint>
   	  <web-resource-collection>
   	  	<web-resource-name>Protected Resource</web-resource-name>
   	    <url-pattern>/*</url-pattern>
   	  </web-resource-collection> 	  
   	  <auth-constraint>
   	     <role-name>manager</role-name>
   	   </auth-constraint>
   	   ...
    </security-constraint>
    ...
    <security-role>
         <role-name>manager</role-name>
    </security-role>
   ```