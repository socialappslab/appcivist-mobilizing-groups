package models;

import java.util.*;
import java.awt.*;
import java.lang.Object;

// todo: add JPA annotations
//@Entity
public class Event {

	public String title;
	public Date startTime;
	public String address;
	//@Id
	private String id;

	// todo: add the Finder
	public Event(String eventTitle, Date eventStartTime, String eventAddress, String eventId){
		this.title = eventTitle;
		this.startTime = eventStartTime;
		this.address = eventAddress;
		this.id = eventId;
	}

}
