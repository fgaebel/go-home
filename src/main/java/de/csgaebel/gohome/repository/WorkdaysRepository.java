package de.csgaebel.gohome.repository;

import java.util.ArrayList;

import de.csgaebel.gohome.model.*;

public class WorkdaysRepository {
	ArrayList<Workday> workdays = new ArrayList<Workday>();
	
	public void setWorkdays(ArrayList<Workday> workdays) {
		this.workdays = workdays;
	}
	
	public ArrayList<Workday> getWorkdays() {
		return workdays;
	}
	
	public void addWorkday(Workday workday){
		
	}
}
