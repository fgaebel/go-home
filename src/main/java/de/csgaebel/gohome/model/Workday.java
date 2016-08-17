package de.csgaebel.gohome.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Workday {
	private LocalDateTime start;
	private LocalDateTime end;
	private int break_in_minutes = 0;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
	
	public Workday (LocalDateTime start) {
		this.start = start;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public int getBreak_in_minutes() {
		return break_in_minutes;
	}
	public void setBreak_in_minutes(int break_in_minutes) {
		this.break_in_minutes = break_in_minutes;
	}
	
	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}
	
	public DateTimeFormatter getFormatter() {
		return formatter;
	}
	
	public String getWorkedPeriodeAsString(){
		Duration timeElapsed;
		
		if(this.getEnd() == null)
			timeElapsed = Duration.between(start, LocalDateTime.now());
		else
			timeElapsed = Duration.between(start, end);
		
		long hours = timeElapsed.toHours();
		long minutes = timeElapsed.minusHours(hours).toMinutes();
		
		return  "Das System läuft heute bereits für " + hours + " Stunden und " + minutes + " Minuten";
	}
	
	public String toString(){
		return formatter.format( start );
	}
}
