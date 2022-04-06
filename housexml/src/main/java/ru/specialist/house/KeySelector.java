package ru.specialist.house;

public class KeySelector
{
	private int aCounter = 0;
	private int bCounter = 0;

	// read-only property
	public String getKey()
	{
		//return "b" + String.valueOf(++bCounter);
		String key = "b" + String.valueOf(++bCounter);
		System.out.printf("Generated key: %s\n", key);
		return key;
	}

	// генератор ключей
	public String getKey(Door door)
	{
		if (door.getClass().equals(MetalDoor.class))
			return "a" + String.valueOf(++aCounter);
		else
			return "b" + String.valueOf(++bCounter);
		
	}
}
