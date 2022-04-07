package ru.home.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Point extends Shape
{
    private Coords coords;

    public Point() {}

    public Point(Coords coords) {
        super();
        this.coords = coords;
    }

    public Coords getCoords()
    {
        return coords;
    }

    @Autowired
    public void setCoords(Coords coords)
    {
        this.coords = coords;
    }

    public double getX()
    {
        return coords.getX();
    }

    public void setX(double x)
    {
        coords.setX(x);
    }

    public double getY()
    {
        return coords.getY();
    }

    public void setY(double y)
    {
        coords.setY(y);
    }

    @Override
    public void draw()
    {
        System.out.printf( "%s point %s\n", this.getColor(),
            coords.toString());
    }
}
