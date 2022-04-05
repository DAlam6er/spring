package ru.specialist.house;

public class Wood implements Material
{
	public Wood()
	{
		System.out.println("Wood created.");
	}

	public void cover()
	{
		System.out.println("Класть бревна");
	}
}
