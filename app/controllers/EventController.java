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
public class EventController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getEvents()
    {
        List<Event> Events = Event.find.all();
        return ok(Json.toJson(Events));
    }

    public static Result getEvent(Long id)
    {
        Event event = Event.find.byId(id);
        // todo: instead of using "Database", just add proper annotations and method to the model Event
        return event == null ? notFound() : ok(Json.toJson(event));
    }

    public static Result createEvent()
    {
        Event newEvent = Json.fromJson(request().body().asJson(), Event.class);
        Event inserted = Database.addEvent(newEvent);
        return created(Json.toJson(inserted));
    }

    public static Result updateEvent(Long id)
    {
        Event Event = Json.fromJson(request().body().asJson(), Event.class);
        Event updated = Database.updateEvent(id, Event);
        return ok(Json.toJson(updated));
    }

    public static Result deleteEvent(Long id)
    {
        Database.deleteEvent(id);
        return noContent(); // http://stackoverflow.com/a/2342589/1415732
    }

}