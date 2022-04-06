package ru.home.building;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy
public class Point extends Shape
{
    private Coords coords;

    public Point(Coords coords) {
        super();
        this.coords = coords;
    }

    public Coords getCoords()
    {
        return coords;
    }

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
        System.out.printf( "%s point %s\n", this.getColor(), coords.toString());
    }
}
