package de.csgaebel.gohome;

import java.time.LocalDateTime;
import java.time.LocalTime;

import de.csgaebel.gohome.model.*;
import de.csgaebel.gohome.repository.*;
import de.csgaebel.gohome.util.*;

public class GoHome {
	public static void main(String[] args) {

		System.out.println("== Willkommen zu GoHome v1 ==\n");
		
		final LocalTime PENSUM = LocalTime.of(7, 36);
		final LocalTime MAX_PENSUM = LocalTime.of(10, 0);
		final LocalTime OFFSET = LocalTime.of(0, 0);
		final LocalTime APPLY_BREAK = LocalTime.of(6, 0);
		
		WorkdayRepository repo = new WorkdayRepository();
		Workday today = new Workday(LocalDateTime.now().minusSeconds(OSUptime.getOSActiveSeconds()));
		
		if(repo.create(today) == -1){
			today = repo.findByDate(LocalDateTime.now());
		}
		
		today.setPensum(PENSUM);
		today.setMax_pensum(MAX_PENSUM);
		today.setOffset(OFFSET);
		today.setApply_break(APPLY_BREAK);
		
		System.out.println(today.getOSUptimeAsString());	
		System.out.println(today.getWorkdayAsString());



	}
	
	
}