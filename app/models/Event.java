package models;

import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import java.util.*;
import java.awt.*;
import java.lang.Object;

@Entity
public class Event extends Model {

	@Constraints.Required
	public String title;

	@Formats.DateTime(pattern="MM/dd/yyyy")
	public Date startTime;

	public String address;

	@Id
	private String id;

	public static Finder<Long, Event> find = new Finder<Long,Event>(Event.class);

	public Event(String eventTitle, Date eventStartTime, String eventAddress, String eventId){
		this.title = eventTitle;
		this.startTime = eventStartTime;
		this.address = eventAddress;
		this.id = eventId;
	}

}
