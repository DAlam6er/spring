package ru.home;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {
        /*
        Point point = new Point();
        point.setCoords(new Coords());
        point.setX(1.0);
        point.setY(1.0);
        point.setColor("red");

        Circle circle = new Circle();
        circle.setCentCoords(new Coords());
        circle.setX(4.0);
        circle.setY(3.0);
        circle.setRad(2.0);
        circle.setColor("green");
        */

        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(
                    "applicationContext.xml");

        Point point = context.getBean("redPoint", Point.class);
        Circle circle = context.getBean("unitCircle", Circle.class);
        //Comparable figure = context.getBean(Comparable.class);

        //System.out.println(point.getCoords());
        point.draw();
        circle.draw();
        //System.out.println(figure);
    }
}
