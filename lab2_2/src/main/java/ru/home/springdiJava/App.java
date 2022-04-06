package ru.home.springdiJava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.home.builder.BuilderConfig;
import ru.home.builder.Circle;
import ru.home.builder.Coords;
import ru.home.builder.Point;

public class App
{
    public static void main(String[] args)
    {
        try (AnnotationConfigApplicationContext context =
                 new AnnotationConfigApplicationContext(BuilderConfig.class))
        {
            System.out.println(context.getBean(Coords.class));
//            Point redPoint = context.getBean("redPoint", Point.class);
//            Circle unitCircle = context.getBean("unitCircle", Circle.class);
            Circle circle = context.getBean(Circle.class);
            Circle circle2 = context.getBean(Circle.class);
            Point point = context.getBean(Point.class);
            Comparable figure = context.getBean(Comparable.class);

//            redPoint.draw();
//            unitCircle.draw();
            circle.draw();
            circle2.draw();
            point.draw();
            System.out.println(figure.getClass());
        }
    }
}
