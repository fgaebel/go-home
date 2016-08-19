package de.csgaebel.gohome.repository;

import java.time.LocalDateTime;
import java.util.List;

import de.csgaebel.gohome.model.Workday;

public interface WorkdayDAO {

	List<Workday> findAll();
    Workday findById(int id);
    Workday findByDate(LocalDateTime date);
	
	int create(Workday workday);
	void update(Workday workday);
	void delete(Workday workday);
}
