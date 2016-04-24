package models;

import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import java.util.*;
import java.awt.*;
import java.lang.Object;

@Entity
public class ResourceBase extends Model {
/**
 * Event, Task, Resource all point to ResourceSpace and ResourceSpace points
 * to an Assembly. So we only need our own version of ResourceSpace; instead
 * of local ID, we use UUID, because this is a separate database
 */

}
