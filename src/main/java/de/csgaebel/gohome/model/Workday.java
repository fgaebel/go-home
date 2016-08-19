package de.csgaebel.gohome.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Workday {
	private int id;
	private LocalDateTime start = null;
	private LocalDateTime end = null;
	private int lunch_break = 0;
	private LocalTime pensum = null;
	private LocalTime max_pensum = null;
	private LocalTime offset = null;
	private LocalTime apply_break = null;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
	
	public Workday (LocalDateTime start) {
		this.start = start;
	}
	
	public Workday() {

	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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
	public int getLunch_break() {
		return lunch_break;
	}

	public void setLunch_break(int lunch_break) {
		this.lunch_break = lunch_break;
	}
	
	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}
	
	public DateTimeFormatter getFormatter() {
		return formatter;
	}
	
	public void setPensum(LocalTime pensum) {
		this.pensum = pensum;
	}
	
	public LocalTime getPensum() {
		return pensum;
	}
	
	public void setMax_pensum(LocalTime max_pensum) {
		this.max_pensum = max_pensum;
	}
	
	public LocalTime getMax_pensum() {
		return max_pensum;
	}
	
	public void setOffset(LocalTime offset) {
		this.offset = offset;
	}
	
	public LocalTime getOffset() {
		return offset;
	}
	
	public void setApply_break(LocalTime apply_break) {
		this.apply_break = apply_break;
	}
	
	public LocalTime getApply_break() {
		return apply_break;
	}
	
	public String getOSUptimeAsString(){
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

	public String getWorkdayAsString() {
		
		LocalTime start = this.getStart().toLocalTime().minusSeconds(this.offset.toSecondOfDay());
		LocalTime now = LocalTime.now();
		LocalTime worked = now.minusSeconds(start.toSecondOfDay());
		
		String retValue = "";
		
		if(worked.isAfter(apply_break)){
			worked = worked.minusMinutes(lunch_break);
			retValue += "(pause applied) ";
		}

		if(worked.isBefore(pensum))
			retValue += "Du bist in der Regelarbeitszeit. " + pensum.minusSeconds(worked.toSecondOfDay()) + " to go!";
		else if(worked.isBefore(max_pensum))
			retValue += "Die Regelarbeitszeit ist überschritten " + max_pensum.minusSeconds(worked.toSecondOfDay()) + " bis zur Abmahnung!";
		else if(worked.isAfter(max_pensum))
			retValue += "Ausstempeln? Besser nicht! Du bist " + worked.minusSeconds(max_pensum.toSecondOfDay()) + " drüber";
		
		return retValue;
		
	}
}
