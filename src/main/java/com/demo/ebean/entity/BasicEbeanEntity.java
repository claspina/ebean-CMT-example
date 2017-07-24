package com.demo.ebean.entity;

import io.ebean.Ebean;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
@Log
public abstract class BasicEbeanEntity {

    @Id
    private Long id;

    public BasicEbeanEntity() {

    }

    public void save() {
        Ebean.save(this);
    }

    public void delete() {
        Ebean.save(this);
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"#"+getId();
    }

    @Override
    public int hashCode() {
        if (id == null)
            throw new IllegalStateException(this.getClass().getSimpleName()+".hashCode() used before having an id");
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;

        if (this.getId() == null)
            throw new IllegalStateException(this.getClass().getSimpleName()+".equals() used before having an id");

        log.info("sefl: "+this.getClass().getSimpleName() + " comparing to "+o.getClass().getSimpleName());

        // TODO/FIXME ebean's enhancement will make names different as hibernate does?
        if (!o.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
            return false;

        BasicEbeanEntity beeo = (BasicEbeanEntity) o;

        if (beeo.getId() == null)
            throw new IllegalStateException(o.getClass().getSimpleName()+".equals() used before having an id");

        return ((BasicEbeanEntity)o).getId().equals(id);

    }
}
