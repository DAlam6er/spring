package ru.home.builder;

public abstract class Shape
{
    private String color;

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void draw() {
        System.out.println(getClass());
    }
}
