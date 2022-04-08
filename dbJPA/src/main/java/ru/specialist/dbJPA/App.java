package ru.specialist.dbJPA;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDAO;

public class App
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        CourseDAO courseDao = context.getBean(CourseDAO.class);
		
		/*
		Course spring = new Course();
		spring.setTitle("Spring");
		spring.setLength(40);
		spring.setDescription("Spring framework");
		courseDao.insert(spring);
		*/
        //courseDao.delete(9);
        //Course s = courseDao.findById(111);
        //s.setLength(36);
        //courseDao.update(s);

        for(Course c : courseDao.findByTitle("web")) {
            System.out.println(c);
        }
        System.out.println("----------------");
        for(Course c : courseDao.findAll()) {
            System.out.println(c);
        }
        //System.out.println( courseDao.findById(5) );

        context.close();
    }
}
