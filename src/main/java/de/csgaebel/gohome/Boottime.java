package de.csgaebel.gohome;

class Boottime
{
    static
    {
    	System.load("C:\\Tools\\Boottime.dll");
    }
    
    public static native long getTickCount();
}