package ru.specialist.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


// Business logic implementation
@Service("courseService")
public class SpringJpaCourseService implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public CourseRepository getCourseRepository() {
		return courseRepository;
	}
	
	@Override
	public void updateLength(int oldLength, int newLength) {
		getCourseRepository().incrementLength(oldLength, newLength);
	}

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course save(Course c) {
		return courseRepository.save(c);
	}

	@Override
	public List<Course> findByTitle(String search) {
		return getCourseRepository().findByTitle("%" + search.trim() + "%");
	}
}