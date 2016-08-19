package de.csgaebel.gohome.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteConnector
{
	static PreparedStatement stmt = null;
	/**
	 * 
	 * @return SqliteConnection
	 */
	public static Connection connect() {

        String url = "jdbc:sqlite:workdays.db";
        Connection conn = null;
        
        try {
        	conn = DriverManager.getConnection(url);

        	String pragma = "PRAGMA foreign_keys = ON";
			stmt = conn.prepareStatement(pragma);
			stmt.executeUpdate();
			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public static void disconnect(Connection connection) {
		try{
			if(connection != null)
				connection.close();
		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void executeStatement(String query, Object parameters[]) {
		
		Connection connection = SqliteConnector.connect();
		
		try{
			stmt = connection.prepareStatement(query);
			int i = 1;
			
			for( Object o: parameters )
			{
				String str = o.getClass().getSimpleName();

				if( str.equals("String") ){
					stmt.setString(i, o.toString());
				}
				if( str.equals("Integer") ){
					stmt.setInt(i, (int)o);
				}
				if ( str.equals("Long") ){
					stmt.setLong(i, (long)o);
				}
				i++;
			}
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			System.err.println(e);
		}
		finally {
			SqliteConnector.disconnect(connection);
		}
	}
}