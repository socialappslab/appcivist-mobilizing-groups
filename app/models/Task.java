package models;

import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import java.util.*;
import java.awt.*;
import java.lang.Object;
import java.util.List;

/**
 * TODO: See Event.java
 */

@Entity
public class Task extends Model {

    private UUID uuid;

    @Constraints.Required
    public String title;

    @Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")
    public Date deadline;

    public User assignedTo; //find User class

    public String details;
    public Boolean isComplete;

    @Id
    private long id;

    public static Finder<Long, Task> find = new Finder<Long,Task>(Task.class);


    public Task(String title, Date deadline, String details, User assignedTo){
        this.title = title;
        this.deadline = deadline;
        this.details = details;
        this.isComplete = false;
        this.uuid = UUID.randomUUID();
        this.assignedTo = assignedTo;
    }


    public static List<Task> getTasks(){
        return find.all();
    }

    public static Task getTask(Long id){
        return find.byId(id);
    }

    public static void delete(Long id) {
        find.ref(id).delete();

}
