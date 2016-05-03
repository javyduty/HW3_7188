/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is an abstract class with an abstract draw method. It inherits from Shape
 * and contains methods needed for drawing ovals and rectangles. It also contains an instance variable called fill.
 */
abstract class BoundedShape extends Shape {
    
    private boolean filled;
    
    /**
     * public constructor that takes no parameters and calls the no parameter constructor for Shape.
     * It sets filled to false.
     */
    public BoundedShape(){
        super();
        filled = false;
    }
    
    /**
     * public overloaded constructor that takes int coordinates and a boolean for filled.
     * It passes the coordinates and color into the constructor for Shape and assigns
     * the filled to an instance variable filled.
     */
    public BoundedShape(int x1, int y1, int x2, int y2, Color color, boolean filled){
        super(x1,y1,x2,y2,color);
        this.filled = filled;
    }
    
    public void setFilled(boolean filled){
        this.filled = filled;
    }
    
    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    
    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    
    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    
    public boolean getFilled(){
        return filled;
    }
    
    /**
     * Abstract method for drawing the shape that must be overriden
     */
    abstract public void draw(Graphics g);
}
