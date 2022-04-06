package ru.specialist.building;

import org.springframework.stereotype.Component;

@Component
public class WoodFrameWindow implements Window {

	public void open() {
		System.out.println("WoodFrame window opened");
	}
	

}
