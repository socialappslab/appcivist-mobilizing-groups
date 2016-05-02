package controllers;

import play.*;
import play.mvc.*;
import play.libs.Json;

import views.html.*;
import models.*;
import java.util.*;
import java.lang.Object;
import com.avaje.ebean.Ebean;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class EventController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public static ResourceSpace resoureceSpace  = new ResourceSpace();

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getEvents(UUID aid)
    {
        List<Event> Events = Event.getEvents();
        return ok(Json.toJson(Events));
    }

    public static Result getEvent(Long id)
    {

        Event someEvent = Event.getEvent(id);
        return someEvent == null ? notFound() : ok(Json.toJson(someEvent));
    }

    public static Result createEvent(Long id)
    {
        //what is newAssemblyForm and how do we do it for Event?
        Event newEvent = Json.fromJson(request().body().asJson(), Event.class);
        Event inserted = Database.createEvent(id, newEvent);
        return created(Json.toJson(inserted));
    }

    public static Result updateEvent(Long id, UUID aid, UUID uid)
    {
        //what is newAssemblyForm and how do we do it for Event?
        Event someEvent = Json.fromJson(request().body().asJson(), Event.class);
        Event.//what update type?
        //Event updated = Database.updateEvent(id, someEvent);
        return ok(Json.toJson(updated));
    }

    public static Result deleteEvent(Long id, UUID aid, UUID uid)
    {
        Ebean.beginTransaction(); //this is optional for single actions, but good practice
        Event.delete(id);
        return ok();
    }

}