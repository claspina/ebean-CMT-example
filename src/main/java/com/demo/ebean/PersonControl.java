package com.demo.ebean;

import io.ebean.Ebean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

/**
 * Created by Carlos on 14/07/2017.
 */
@Stateless
public class PersonControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonControl.class);

    public String createPerson(){

        LOGGER.info("Creating person...");

        PersonEntity person = new PersonEntity();
        person.setAge(25);
        person.setName("Carlos");
        person.setLastName("Laspina");

        Ebean.save(person);

        LOGGER.info("Person created...");

        return "creado";
    }
}
