package de.csgaebel.gohome.util;
/**
 * Author: Domenico Monaco, Yong Mook Kim
 *  
 * Source: https://gist.github.com/kiuz/816e24aa787c2d102dd0
 *  
 * License: GNU v2 2014
 *
 * Fork / Learned: http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
 *
 */
public class OSValidator {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }
    
    public static String getOS(){
    	if (isWindows()) return "win";
    	if (isMac())     return "osx";
    	if (isUnix())    return "uni"; 
    	if (isSolaris()) return "sol";

    	return "err";
        
    }

}