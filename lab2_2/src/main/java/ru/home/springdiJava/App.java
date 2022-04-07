package ru.home.springdiJava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.home.builder.BuilderConfig;
import ru.home.builder.Scene;

public class App
{
    public static void main(String[] args)
    {
        try (AnnotationConfigApplicationContext context =
                 new AnnotationConfigApplicationContext(BuilderConfig.class))
        {
            context.getBean(Scene.class).draw();
        }
    }
}
