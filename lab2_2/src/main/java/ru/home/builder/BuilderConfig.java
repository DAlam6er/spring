package ru.home.builder;

import org.springframework.context.annotation.*;

@Configuration
public class BuilderConfig
{
    @Bean
    @Scope("prototype")
    public Coords coords() {
        return new Coords();
    }

    @Bean
    @Scope("prototype")
    @Primary
    //@Lazy
    public Point point(Coords coords) {
        return new Point(coords);
    }

    @Bean("redPoint")
    @Scope("prototype")
    public Point point2() {
        return new Point();
    }

    @Bean("yellowPoint")
    @Scope("prototype")
    public Point point3() {
        Point point = new Point();
        point.setColor("yellow");
        return point;
    }

    @Bean
    @Scope("prototype")
    @Primary
    //@Lazy
    public Circle circle()
    {
        return new Circle();
    }

    @Bean
    @Scope("prototype")
    //@Lazy
    public Circle unitCircle()
    {
        Circle circle = new Circle(new Coords(), 1.0);
        circle.setColor("red");
        return circle;
    }

    @Bean
    @Lazy
    public Scene scene()
    {
        Scene myScene = new Scene();
        //myScene.addShape(new Point());
        myScene.addShape(point(coords()));
        myScene.addShape(point2());
        myScene.addShape(point3());
        myScene.addShape(circle());
        myScene.addShape(unitCircle());
        return myScene;
    }
}
