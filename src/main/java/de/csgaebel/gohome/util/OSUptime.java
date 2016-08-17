package de.csgaebel.gohome.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OSUptime {
	
	private static long getWindowsUptime() throws Exception {
		long uptime = -1;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			Process uptimeProc = Runtime.getRuntime().exec("net stats srv");
			BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.startsWith("Statistik seit")) {

					SimpleDateFormat format = new SimpleDateFormat("'Statistik seit' dd.MM.yyyy hh:mm:ss");
					Date boottime = format.parse(line);
					uptime = System.currentTimeMillis() - boottime.getTime();
					break;
				}
			}
		}
		return uptime/1000;
	}

	private static long getUnixUptime() {
		// TODO implement OSUptime.getUnixUptime() for Linux Systems
		return 0;
	}
	
	/**
	 * Based on the active OS, different methods will be used to calculate    
	 * how long the system is running since the last (re)start.  
	 * 
	 * @return <b>long</b> - the time the operating system is active since the last start in seconds
	 */
	public static long getOSActiveSeconds() {
		long os_active_seconds = 0;
		
		if (OSValidator.isWindows()) {
			try {
				os_active_seconds = getWindowsUptime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OSValidator.isUnix()) {
				os_active_seconds = getUnixUptime();
		} else {
			System.out.println("Your OS is not support!!");
		}
		return os_active_seconds;
	}
}
