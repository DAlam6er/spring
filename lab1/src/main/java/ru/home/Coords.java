package ru.home;

public class Coords
{
    private double x;
    private double y;

    public Coords() {}

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }
}
