package ru.specialist.dao;

import java.util.List;

// Business logic declaration
public interface CourseService {
	CourseRepository getCourseRepository();
	List<Course> findAll();
	
	List<Course> findByTitle(String search);
	Course save(Course course);
	
	void updateLength(int oldLength, int newLength);
	
	/*
	void insert(Course course);
	Course findById(int id);
	List<Course> findByTitle(String title);
	void delete(int id);
	*/
}
