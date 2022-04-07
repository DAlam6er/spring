package ru.specialist.dbJDBC;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDAO;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");

		// Запрашиваем у контейнера Spring нечто, реализующее интерфейс CourseDAO
		CourseDAO courseDao = context.getBean(CourseDAO.class);

		// Добавление нового курса в БД
		/*
		Course nc = new Course();
		nc.setTitle("Spring");
		nc.setLength(40);
		nc.setDescription("Spring framework");

		// до сохранения id NULL
		System.out.println(nc);
		courseDao.insert(nc);
		// после сохранения id
		System.out.println(nc);
		System.out.println("------------------");
		*/

		/*
		Course s = courseDao.findById(8);
		// изменим длительность курса
		s.setLength(42);
		courseDao.update(s);
		*/

		// Удаление курса из БД
		//courseDao.delete(8);

		for(Course c : courseDao.findByTitle("web")) {
			System.out.println(c);
		}
		System.out.println("------------------");
		for(Course c : courseDao.findAll()) {
			System.out.println(c);
		}

		int id = 4;
		System.out.printf("Курс %d:\n%s\n", id, courseDao.findById(id));

		context.close();
	}
}
