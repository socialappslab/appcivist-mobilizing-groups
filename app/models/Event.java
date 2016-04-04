package models;

import java.util.*;
import java.awt.*;
import java.lang.Object;

public class Event {
	public String title;
	public Date startTime;
	public String address;
	private String id;

	public Event(String eventTitle, Date eventStartTime, String eventAddress, String eventId){
		self.title = eventTitle;
		self.startTime = eventStartTime;
		self.address = eventAddress;
		self.id = eventId;
	}
}
