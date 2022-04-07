package ru.home.builder;

import org.springframework.beans.factory.annotation.Value;

public class Coords
{
    private double x;
    private double y;

    public Coords() {}

    public double getX()
    {
        return x;
    }

    @Value("#{T(java.lang.Math).random() *100}")
    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    @Value("#{T(java.lang.Math).random() *100.0}")
    public void setY(double y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "(" + String.format("%.2f", getX()) + "; " +
            String.format("%.2f", getY()) + ")";
    }
}
