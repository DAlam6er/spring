package ru.specialist.springdiJava;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import ru.specialist.graph.*;

@Configuration
public class GraphConfig {
	
	@Bean("default")
	public Coords coords() {
		return new Coords(100, 200);
	}
	
	@Bean
	@Scope("prototype")
	@Lazy
	public Coords coords(@Value("#{default.x}") int x, @Value("5") int y) {
		return new Coords(x, y);
	}
	
	
	@Bean
	//@Scope("prototype")
	@Lazy
	public Point point() {
		Point p = new Point(coords(10, 20));
		p.setColor(Shape.DEFAULT_COLOR);
		return p;
	}
	
	@Bean
	@Scope("prototype")
	@Lazy
	public Circle circle(int r) {
		Circle c = new Circle(coords(20, 30));
		c.setColor(Shape.DEFAULT_COLOR);
		c.setR(r);
		return c;
	}
	
	@Bean
	@Scope("singleton")
	public Scene scene() {
		Scene s = new Scene();
		
		s.setObjects(new ArrayList<Shape>());
		
		s.getObjects().add(point());
		s.getObjects().add(circle(15));
		
		return s;
	}


}
