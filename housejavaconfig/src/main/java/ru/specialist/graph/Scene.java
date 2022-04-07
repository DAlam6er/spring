package ru.specialist.graph;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Scene implements ApplicationContextAware, DisposableBean{
	
	private List<Shape> objects;
	public List<Shape> getObjects() {
		return objects;
	}

	public void setObjects(List<Shape> objects) {
		this.objects = objects;
	}
	
	public void draw() {
		for(Shape s : getObjects() )
			s.draw();
		
	}
	
	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		//applicationContext.getBean
		appContext = applicationContext;
	}
	
	@PostConstruct
	public void onCreate()
	{
		System.out.printf("Scene on create size: %d\n", getObjects().size());
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("on scene destroy");
		
	}

}
