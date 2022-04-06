package ru.specialist.springdiJava;

import java.io.Closeable;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.specialist.builder.Brick;
import ru.specialist.builder.BuilderConfig;
import ru.specialist.builder.House;
import ru.specialist.builder.Material;
import ru.specialist.builder.Window;
import ru.specialist.builder.Wood;
import ru.specialist.graph.Coords;
import ru.specialist.graph.Scene;

/* Coords
 * Point
 * Circle
 * 
 * Scene
 * 	List<Shape>
 * 
 *  draw
 * 
 * 
 * 
 */

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(BuilderConfig.class);

		for( Map.Entry<String,Material> c : context.getBeansOfType(Material.class).entrySet())
		{
			System.out.printf("%s : %s\n", c.getKey(), c.getValue().getClass().getName());
		}

    	House house = context.getBean(House.class);

		house.buildWall();
		house.view();

		/*System.out.println(context.getBean("stena", Material.class));
		System.out.println(context.getBean("stena", Material.class));
		System.out.println("----");
		System.out.println(context.getBean("stena", Brick.class));
		System.out.println(context.getBean("logs", Wood.class));*/
		//System.out.println(context.getBean(Window.class));
		context.close();
		
		try (AnnotationConfigApplicationContext gContext =
				new AnnotationConfigApplicationContext(GraphConfig.class))
		{
		
			gContext.getBean(Scene.class).draw();
			System.out.println(gContext.getBean("coords",Coords.class).getX());
		}
		
		//((Closeable)gContext).close();

    }
}
