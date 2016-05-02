package models;

import javax.persistence.*;
<<<<<<< HEAD

=======
import com.avaje.ebean.annotation.*;
>>>>>>> 939c28ad71587d4aed208579a605a661dddab338
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.Index;

import play.data.format.*;
import play.data.validation.*;

import java.util.*;
import java.awt.*;
import java.lang.Object;
<<<<<<< HEAD
import java.util.UUID;

import enums.EventRegisterTypes;
import enums.EventVisibilityTypes;

import play.mvc.*;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
=======
import java.util.List;
>>>>>>> 939c28ad71587d4aed208579a605a661dddab338

@Entity
public class Event extends Model {

	@Inject WSClient ws;

	@Required
	public String title;


	@Required @Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")

	public Date startTime;

	@Required @Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")
	public Date endTime;

	public String currency = "USD";

	public String address;

	@Required
	public UUID creatorId;


	/* To add: public User creator, public List<User> participants, public Assembly assembly */

	@Index
	private UUID uuid = UUID.randomUUID();

	@Id @GeneratedValue
	private long id;

	private long eventbriteId;


	public static Finder<Long, Event> find = new Finder<Long,Event>(Event.class);

	public EventRegisterTypes registerType = OPEN;

	public  EventVisibilityTypes visibility = PUBLIC;

	private String eventbriteToken;

	public String timezone = "America/Los_Angeles";

	public Event(String title, Date startTime, Date endTime, String address, String currency, UUID creator, String token){
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.address = address;
		this.currency=currency;
		this.creatorId = creator;
		this.eventbriteToken = token;
		this.createOnEventbrite();
	}

	public Event(String title, Date startTime, Date endTime, String address, String currency, UUID creator, String token, EventRegisterTypes register, EventVisibilityTypes visible){
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.address = address;
		this.currency=currency;
		this.creatorId = creator;
		this.eventbriteToken = token;
		this.registerType = register;
		this.visibility = visible;
		this.createOnEventbrite();
	}	

	private long createOnEventbrite(){
		WSRequest request = ws.url("https://www.eventbrite.com/events/");
		request = request.setHeader("Authorization", "Bearer "+this.eventbriteToken);
		request = request.setQueryParameter("event.name.html", this.title);
		request = request.setQueryParameter("event.start.utc", this.startTime);
		request = request.setQueryParameter("event.start.timezone", this.timezone);
		request = request.setQueryParameter("event.end.utc",this.endTime);
		request = request.setQueryParameter("event.end.timezone",this.timezone);
		
	}
	
	public static List<Event> getEvents(){
		return find.all();
	}
	/* Share event? Frontend or Backend? */

	public static Event getEvent(Long id){
		return find.byId(id);
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
