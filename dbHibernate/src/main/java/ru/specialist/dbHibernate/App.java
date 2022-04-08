package ru.specialist.dbHibernate;

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

        //courseDao.delete(8);

        for(Course c : courseDao.findByName("web")) {
            System.out.println(c);
        }
        /*
        for(Course c : courseDao.findAll()) {
            System.out.println(c);
        }
        */

        /*
        Course c8 = courseDao.findById(8);
        System.out.println( c8 );
        c8.setLength(45);
        courseDao.update(c8);
        */
        context.close();
    }
}
