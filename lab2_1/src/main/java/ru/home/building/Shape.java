package ru.home.building;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public abstract class Shape
{
    private String color;

    public String getColor()
    {
        return color;
    }

    @Value("red")
    public void setColor(String color)
    {
        this.color = color;
    }

    public void draw() {}

}
