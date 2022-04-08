package ru.specialist.dbSpringData;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseService;

public class App 
{
    public static void main( String[] args )
    {
    	ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		
		CourseService courseService = context.getBean(
				"courseService",
				CourseService.class);
		
		// courseService.updateLength(0, 0);

		for(Course c : courseService.findByTitle("web")) {
			System.out.println(c);
		}
		System.out.println("------------------");
		for(Course c : courseService.findAll()) {
			System.out.println(c);
		}

		context.close();     }
}
