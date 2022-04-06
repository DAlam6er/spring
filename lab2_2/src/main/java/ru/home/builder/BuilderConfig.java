package ru.home.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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
    @Lazy
    public Point point() {
        return new Point(coords());
    }

    @Bean
    @Scope("prototype")
    @Lazy
    public Circle circle()
    {
        return new Circle(coords(), 0);
    }

    public Scene scene()
    {
        return new Scene();
    }
}
