package ru.specialist.graph;

import org.springframework.beans.factory.annotation.Value;

public class Circle extends Shape {
	private Coords center;
	private int r;
	
	
	public Coords getCenter() {
		return center;
	}
	public void setCenter(Coords center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public Circle(Coords center) {
		this(center, 0);
	}
	public Circle(Coords center, int r) {
		super();
		this.center = center;
		this.r = r;
	}
	
	public int getX() {
		return getCenter().getX();
	}
	
	//@Value("#{point.x}")
	public void setX(int y) {
		getCenter().setX(y);
	}
	public int getY() {
		return getCenter().getY();
	}
	
	//@Value("#{point.y}")
	public void setY(int y) {
		getCenter().setY(y);
	}
	
	public void draw() {
		System.out.printf("Circle (%d, %d) R: %d Color: %s\n", 
				getX(), getY(), getR(), getColor());
		
	}
}
