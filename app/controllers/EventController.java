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
    public static ResourceSpace resoureceSpace  = new ResourceSpace();

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getEvents(UUID aid)
    {
        List<Event> Events = Event.find.all();
        return ok(Json.toJson(Events));
    }

    public static Result getEvent(Long id)
    {
        Event Event = Event.find.byId(id);
        // todo: instead of using "Database", just add proper annotations and methods to the model Event
        return Event == null ? notFound() : ok(Json.toJson(Event));
    }

    public static Result createEvent(UUID aid, UUID uid)
    {
        /* Query Parsing for arguments */
        User creator = User.findByAuthUserIdentity(PlayAuthenticate.getUser(session()));
        final Form<AssemblyTransfer> newEventForm = ASSEMBLY_TRANSFER_FORM.bindFromRequest();
        Event newEvent = Json.fromJson(request().body().asJson(), Event.class);
        return created(Json.toJson(newEvent));
    }

    public static Result updateEvent(Long id, UUID aid, UUID uid)
    {
        /* Query Parsing for arguments */
        Event event = Json.fromJson(request().body().asJson(), Event.class);
        return ok(Json.toJson(event));
    }

    public static Result deleteEvent(Long id, UUID aid, UUID uid)
    {
        find.ref(id).delete();
        return noContent(); // http://stackoverflow.com/a/2342589/1415732
    }

}