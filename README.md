# go-home
java training project

### Done
- Create WorkDay Class
- Get startup time and calculate uptime for the current day
-	Add working_hours and break support
-	Store workdays in a local db

###ToDo

-	Implement *nix Version of getUnixUptime()
-	Display past workdays using a gui

Please note if the power option "fast startup" is active this program will most likely display 
false data. (Source: https://www.pipperr.de/dokuwiki/doku.php?id=windows:windows_uptime)

JRE 1.8 is required to run go-home

###Usage

1. Create GetHome.jar
2. Create batch file GoHome.bat 

`@echo off`
`java -jar GoHome.jar`
`pause`
