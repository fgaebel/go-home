package de.csgaebel.gohome;

import java.time.LocalDateTime;

import de.csgaebel.gohome.model.*;

public class GoHome {
	public static void main(String[] args) {
//		Create Workday class 
//	    Get the current system time (startup)
//	    TODO Persist Workday objects (database, xml, json?)
//	    TODO Add "break" support ( if Period > 6h add 45 minutes )
//	    TODO Display preceded Workdays
//		TODO Add property for the maximum work amount allowed on a day 
//		TODO Add warning messages based on the time remaining
//		TODO Support restart of a system
		
//		FIXME de.csgaebel.go.home.utils.GoHome.getTickCount()J

		
		System.out.println("== Willkommen zu GoHome v1 ==\n");
		long seconds = 0;
		
		try {
			 seconds = (Boottime.getTickCount() / 1000);
		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
		}
		
		Workday today = new Workday(LocalDateTime.now().minusSeconds(seconds));
		System.out.println("Aktuelle Zeit:" + LocalDateTime.now());
		System.out.println(today.getWorkedPeriodeAsString());
	}

}
