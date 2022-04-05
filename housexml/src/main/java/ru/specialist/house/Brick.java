package ru.specialist.house;

public class Brick implements Material
{
	public Brick()
	{
		System.out.println("Brick created");
	}

	public void cover()
	{
		System.out.println("Класть кирпич");
	}
}
