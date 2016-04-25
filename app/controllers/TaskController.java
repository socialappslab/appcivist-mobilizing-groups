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
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getTasks()
    {
        List<Task> Tasks = Database.getTasks();
        return ok(Json.toJson(Tasks));
    }

    public static Result getTask(Long id)
    {
        Task Task = Database.getTask(id);
        // todo: instead of using "Database", just add proper annotations and method to the model Task
        return Task == null ? notFound() : ok(Json.toJson(Task));
    }

    public static Result createTask()
    {
        Task newTask = Json.fromJson(request().body().asJson(), Task.class);
        Task inserted = Database.addTask(newTask);
        return created(Json.toJson(inserted));
    }

    public static Result updateTask(Long id)
    {
        Task Task = Json.fromJson(request().body().asJson(), Task.class);
        Task updated = Database.updateTask(id, Task);
        return ok(Json.toJson(updated));
    }

    public static Result deleteTask(Long id)
    {
        Database.deleteTask(id);
        return noContent(); // http://stackoverflow.com/a/2342589/1415732
    }

}