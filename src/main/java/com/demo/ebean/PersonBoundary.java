package com.demo.ebean;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Carlos on 14/07/2017.
 */
@Stateless
public class PersonBoundary {

    @Inject
    private PersonControl personControl;

    public String createPeron(){
        return personControl.createPerson();
    }

}
