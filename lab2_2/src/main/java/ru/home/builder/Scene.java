package ru.home.builder;

import java.util.ArrayList;
import java.util.List;

public class Scene
{
    private final List<Shape> shapes = new ArrayList<>();

    public List<Shape> getShapes()
    {
        return shapes;
    }

    public void addShape(Shape shape)
    {
        shapes.add(shape);
    }

    public void draw()
    {
        for (Shape shape : shapes ) {
            shape.draw();
        }
    }
}
