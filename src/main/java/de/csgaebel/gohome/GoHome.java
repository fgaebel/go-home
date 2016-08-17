package de.csgaebel.gohome;

import java.time.LocalDateTime;

import de.csgaebel.gohome.model.*;
import de.csgaebel.gohome.util.*;

public class GoHome {
	public static void main(String[] args) {

		System.out.println("== Willkommen zu GoHome v1 ==\n");

		Workday today = new Workday(LocalDateTime.now().minusSeconds(OSUptime.getOSActiveSeconds()));
		System.out.println(today.getWorkedPeriodeAsString());
	}
	
	
}