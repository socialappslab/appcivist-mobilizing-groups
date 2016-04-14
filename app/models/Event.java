package models;

import javax.persistence.*;
import com.avaje.ebean.annotation.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import java.util.*;
import java.awt.*;
import java.lang.Object;

@Entity
public class Event extends Model {

	@Index
	public UUID uuid;

	@Constraints.Required
	public String title;

	@Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")

	public Date startTime;

	@Formats.DateTime(pattern= "MM/dd/yyyy HH:mm:ss")
	public Date endTime;

	public String currency = "USD";

	public String address;

	public static Finder<Long, Event> find = new Finder<Long,Event>(Event.class);


	public Event(String title, Date startTime, Date endTime, String address, String currency){
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.address = address;
		this.currency = currency;
		this.uuid = UUID.randomUUID();
	}
}
