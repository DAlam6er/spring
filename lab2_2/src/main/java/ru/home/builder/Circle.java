package ru.home.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circle extends Shape implements Comparable<Circle>
{
    private Coords centCoords;
    private double rad;

    public Circle () {}

    public Circle(Coords centCoords,
                  @Value("#{T(java.lang.Math).random() * 100}") double rad)
    {
        this.centCoords = centCoords;
        this.rad = rad;
    }

    public Coords getCentCoords()
    {
        return centCoords;
    }

    @Autowired
    public void setCentCoords(Coords centCoords)
    {
        this.centCoords = centCoords;
    }

    public double getX()
    {
        return centCoords.getX();
    }

    public void setX(double x)
    {
        centCoords.setX(x);
    }

    public double getY()
    {
        return centCoords.getY();
    }

    public void setY(double y)
    {
        centCoords.setY(y);
    }

    public double getRad() {
        return rad;
    }

    public void setRad(double rad) {
        this.rad = rad;
    }

    @Override
    public void draw()
    {
        StringBuilder strB = new StringBuilder();
        strB.append(getColor()).append(" Circle (")
            .append(String.format("%.2f", getX())).append("; ")
            .append(String.format("%.2f", getY())).append("; ")
            .append(String.format("%.2f", getRad())).append(")");
        System.out.println(strB);
    }

    @Override
    public int compareTo(Circle circle) {
        return (int) (getRad() - circle.getRad());
    }
}
