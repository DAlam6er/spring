package ru.specialist.building;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("logsBean")
@Component("logs")
//@Component // name="wood" 
public class Wood implements Material{
	
	public void cover() {
		System.out.println("Класть бревна");
		
	}
	

}
