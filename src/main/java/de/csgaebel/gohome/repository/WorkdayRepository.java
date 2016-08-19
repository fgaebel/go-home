package de.csgaebel.gohome.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import de.csgaebel.gohome.model.Workday;
import de.csgaebel.gohome.util.SqliteConnector;

public class WorkdayRepository implements WorkdayDAO {

	Connection connection = null;
	PreparedStatement stmt = null;
	
	public WorkdayRepository() {}
	
	@Override
	public List<Workday> findAll() {
		
		List<Workday> workdays = new ArrayList<Workday>();
		
		try 
		{
			String query = "SELECT * FROM workdays";

			connection = SqliteConnector.connect();
			stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Workday workday = new Workday();
				workday.setId(rs.getInt("id"));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
				LocalDateTime start = LocalDateTime.parse(rs.getString("start"), formatter);

				workday.setStart(start);
				workday.setId(rs.getInt("lunch_break"));
				
				workdays.add(workday);
			}
			
			return workdays;
		}	
		catch(Exception e)
		{
			System.out.println("Something went wrong (findAll()): " + e.toString());
		}
		finally
		{
			SqliteConnector.disconnect(connection);
		}
		
		return null;
	}

	@Override
	public Workday findById(int id) {
		try 
		{
			String query = "SELECT * FROM workdays WHERE id = ?";
			connection = SqliteConnector.connect();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			Workday workday = new Workday();
			while(rs.next()){

				workday.setId(rs.getInt("id"));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
				LocalDateTime start = LocalDateTime.parse(rs.getString("start"), formatter);

				workday.setStart(start);
				workday.setLunch_break((rs.getInt("lunch_break")));
				
			}
			return workday;
		}	
		catch(Exception e)
		{
			System.out.println("Something went wrong (findAll()): " + e.toString());
		}
		finally
		{
			SqliteConnector.disconnect(connection);
		}
		
		return null;
	}

	@Override
	public Workday findByDate(LocalDateTime date) {
		List<Workday> workdays = this.findAll();
		
		for (Workday workday : workdays) {
			if((date.toLocalDate().compareTo((workday.getStart().toLocalDate())) == 0))
				return workday;
		}
		
		return null;
	}

	@Override
	public int create(Workday workday) {
		int index = 0;
		
		if (this.findByDate(workday.getStart()) != null){
				return -1;
		}
		
		try
		{
			connection = SqliteConnector.connect();
			String query = "INSERT INTO "
					+ "workdays (start, lunch_break) "
					+ "VALUES (?, ?)";
			
			stmt = connection.prepareStatement(query); 
		
			stmt.setString(1, workday.toString());
			stmt.setInt(2, workday.getLunch_break());
			
			stmt.executeUpdate();
			
			index = stmt.getGeneratedKeys().getInt(1);
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong: (create(Workday workday)) " + e.toString());
		}
		finally
	    {
			SqliteConnector.disconnect(connection);
	    }
		return index;
	}

	@Override
	public void update(Workday workday) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Workday workday) {
		try 
		{
			String query = "DELETE FROM workdays WHERE id = ?";

			connection = SqliteConnector.connect();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, workday.getId());
			
			stmt.execute();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong (delete(Workday workday)): " + e.toString());
		}
		finally
		{
			SqliteConnector.disconnect(connection);
		}
	}

}
