package com.demo.ebean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Carlos on 14/07/2017.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class PersonEntity {

    @Id
    private Integer id;
    private String name;
    private String lastName;
    private Integer age;
}
