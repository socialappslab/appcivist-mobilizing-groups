package models;

import javax.persistence.*;

import com.avaje.ebean.annotation.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.Index;

import play.data.format.*;
import play.data.validation.*;

import java.util.*;
import java.awt.*;
import java.lang.Object;
import java.util.UUID;

import enums.EventRegisterTypes;
import enums.EventVisibilityTypes;

import play.mvc.*;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import java.util.List;


@Entity
public class Event extends Model {

	@Inject WSClient ws;

	@Required
	public String title;


	@Required @Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")

	public Date startTime;

	@Required @Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")
	public Date endTime;

	/**
	 * TODO: make currency adaptive
	 * right now, it's USD by default
	 * You can use address info to get the 3 digit ISO code
	 */
	public String currency = "USD";

	public String address;

	@Required
	public UUID creatorId;


	/* TODO add: public User creator, public List<User> participants, public Assembly assembly */

	@Index
	private UUID uuid = UUID.randomUUID();

	@Id @GeneratedValue
	private long id;

	private long eventbriteId;


	public static Finder<Long, Event> find = new Finder<Long,Event>(Event.class);

	public EventRegisterTypes registerType = OPEN;

	public  EventVisibilityTypes visibility = PUBLIC;

	private String eventbriteToken;

	//TODO: make timezone adaptive based on address, as with currency above
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
	/* TODO: Share event. Enable service of sending emails? */

	public static Event getEvent(Long id){
		return find.byId(id);
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
