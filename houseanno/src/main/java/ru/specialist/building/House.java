package ru.specialist.building;

import java.util.Collection;
import java.util.Map;

// Java EE
import javax.inject.Inject;
import javax.inject.Named;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * Coords
 *  (x,y)
 *  0 ... 999
 *  
 * Point (myPoint)
 * 	coords
 * 
 * Circle
 * 	centerCoords
 *   R ()
 * 
 * 
 * 
 */

@Component
//@Scope("prototype")
//@Lazy
public class House{
	private Window window;
	
	//private Collection<Door> doors;
	//private List<Door> doors;
	//private Door[] doors;
	
	private Map<String,Door> doors;
	
	@Autowired
	//@Autowired(required=false)
	// стены будут из кирпича
	// связывать с бином с именем "brick"
	//@Qualifier("brick")
	// стены будут из дерева
	//@Value("#{logs}")
	//@Value("#{wood}")
	@WoodQualifier
	// Java EE annotations:
	//@Inject // javax.inject
	//@Resource(name="logsBean")
	//@Named("logs") // javax.inject
	private Material wall;
	
	//@Value("3") // можно указывать SpEL !!
	private int height;
	
	
	public void printHeight() {
		System.out.printf("House height: %d\n", getHeight());
		
	}
	
	//@Autowired
	// аннотацию можно навесить на параметр конструктора
	public House(Window window, @Value("4") int height) {
		System.out.printf("ctor House. window: %s\n", window);
		this.window = window;
		this.height = height;
	}
	
	public void view() {
		window.open();
	}
	
	public void buildWall() {
		for(int i=1; i <= getHeight(); i++) {
			System.out.printf("Этаж %d. ", i);
			wall.cover();
		}
	}
	
	public void installDoors() {
		//for(Door door : doors)
		//	door.install();
		
		for(Map.Entry<String, Door> e : doors.entrySet()) {
			System.out.printf("Ключ %s. ", e.getKey());
			e.getValue().install();
		}
			
	}
	

	public Material getWall() {
		return wall;
	}

	//@Autowired
	//@Qualifier("logsBean")
	//@Qualifier("logs")
	public void setWall(Material wall) {
		this.wall = wall;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public Map<String,Door> getDoors() {
		return doors;
	}

	public void setDoors(Map<String,Door> doors) {
		this.doors = doors;
	}
	
	public void destroy() {
		System.out.println("Снести здание");
	}

	
	/*public Collection<Door> getDoors() {
		return doors;
	}

	public void setDoors(Collection<Door> doors) {
		this.doors = doors;
	}*/
	
	
	
	
	
	
	
}
