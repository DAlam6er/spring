package ru.home;

public class Circle extends Shape implements Comparable<Circle>
{
    private Coords centCoords;
    private double rad;


    public Circle(Coords centCoords, double rad) {
        this.centCoords = centCoords;
        this.rad = rad;
    }

    public Coords getCentCoords()
    {
        return centCoords;
    }

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
        centCoords.setX(y);
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
        strB.append(getColor()).append(" Circle (").append(getX())
            .append(", ").append(getY()).append(", ")
            .append(getRad()).append(")");
        System.out.println(strB);
    }

    @Override
    public int compareTo(Circle circle) {
        return (int) (getRad() - circle.getRad());
    }
}
