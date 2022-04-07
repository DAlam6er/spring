package ru.home.springdiAnno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.home.building.Circle;
import ru.home.building.Coords;
import ru.home.building.Point;

@ComponentScan("ru.home.building")
public class App
{
    public static void main(String[] args)
    {
        try (AnnotationConfigApplicationContext context =
                 new AnnotationConfigApplicationContext(App.class))
        {
            System.out.println(context.getBean(Coords.class));
            Point redPoint = context.getBean("bluePoint", Point.class);
            Circle unitCircle = context.getBean("unitCircle", Circle.class);
            Circle circle = context.getBean(Circle.class);
            Point point = context.getBean(Point.class);
            Comparable figure = context.getBean(Comparable.class);

            redPoint.draw();
            point.draw();
            unitCircle.draw();
            circle.draw();

            System.out.println(figure.getClass());
        }
    }
}
