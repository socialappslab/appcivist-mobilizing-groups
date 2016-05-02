package models;

import javax.persistence.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.Index;

import play.data.format.*;
import play.data.validation.*;

import java.util.*;
import java.awt.*;
import java.lang.Object;
import java.util.UUID;



@Entity
public class ResourceSpace extends Model{

    /**
     * The find property is an static property that facilitates database query
     * creation
     */
    public static Finder<Long, ResourceSpace> find = new Finder<>(
            ResourceSpace.class);

    private UUID uuid;
    public ResourceSpaceTypes type;
    public UUID parent;

    public ResourceSpace() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public ResourceSpace(ResourceSpaceTypes type) {
        super();
        this.type = type;
        this.uuid = UUID.randomUUID();
    }

    public ResourceSpace(ResourceSpaceTypes type, UUID parent) {
        super();
        this.type = type;
        this.parent = parent;
        this.uuid = UUID.randomUUID();
    }

}