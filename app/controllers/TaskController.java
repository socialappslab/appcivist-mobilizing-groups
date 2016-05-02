package controllers;

import play.*;
import play.mvc.*;
import play.libs.Json;

import views.html.*;
import models.*;
import java.util.*;
import java.lang.Object;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TaskController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public static ResourceSpace resourceSpace  = new ResourceSpace();

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getTasks(UUID aid)
    {
        List<Task> Tasks = Task.getTasks();
        return ok(Json.toJson(Tasks));
    }

    public static Result getTask(Long id)
    {

        Task someTask = Task.getTask(id);
        return someTask == null ? notFound() : ok(Json.toJson(someTask));
    }

    public static Result createTask(Long id)
    {
        //what is newAssemblyForm and how do we do it for Task?
        Task newTask = Json.fromJson(request().body().asJson(), Task.class);
        Task inserted = Database.createTask(id, newTask);
        return created(Json.toJson(inserted));
    }

    public static Result updateTask(Long id, UUID aid, UUID uid)
    {
        //what is newAssemblyForm and how do we do it for Task?
        Task someTask = Json.fromJson(request().body().asJson(), Task.class);
        Task.//what update type?
        //Task updated = Database.updateTask(id, someTask);
        return ok(Json.toJson(updated));
    }

    public static Result deleteTask(Long id, UUID aid, UUID uid) {
        Ebean.beginTransaction(); //this is optional for single actions, but good practice
        Task.delete(id);
        return ok();
    }

}