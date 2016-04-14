package models;

import javax.persistence.*;
import com.avaje.ebean.Model;
import com.sun.org.apache.xpath.internal.operations.Bool;
import play.data.format.*;
import play.data.validation.*;
import java.util.*;
import java.awt.*;
import java.lang.Object;

@Entity
public class Task extends Model {

    private static long currentId = 1;
    //retrieve from backend?
    @Constraints.Required
    public String title;

    @Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")
    public Date deadline;

    public String details;
    public Boolean isComplete = false;

    @Id
    private long id;

    public static Finder<Long, Task> find = new Finder<Long,Task>(Task.class);


    public Task(String title, Date deadline, String details, Boolean isComplete){
        this.title = title;
        this.deadline = deadline;
        this.isComplete = isComplete;
        this.id = currentId;

    }

}
