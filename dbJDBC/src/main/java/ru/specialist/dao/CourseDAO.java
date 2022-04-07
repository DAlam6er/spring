package ru.specialist.dao;

import java.util.List;

// интерфейс, описывающий репозиторий
public interface CourseDAO {
	
	Course findById(int id);
	List<Course> findAll();
	
	// CRUD(create, update, delete)
	List<Course> findByTitle(String title);
	void insert(Course course);
	void update(Course course);
	void delete(int id);

}
