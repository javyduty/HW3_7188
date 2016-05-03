/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class contains int coordinates and a Color color. It has accessor and mutator methods for them.
 */
public abstract class Shape {
    
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected Color myColor;
    protected boolean fill;
    
    public Shape(){
        this(0, 0, 0, 0, Color.BLACK);
    }
    
    public Shape( int x1, int y1, int x2, int y2, Color color){
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
    }
    
    public void setX1(int x1){
        this.x1 = (x1 >= 0 ? x1 :0);
    }
    
    public int getX1(){
        return x1;
    }
    
    public void setX2(int x2){
        this.x2 = (x2 >= 0 ? x2 :0);
    }
    
    public int getX2(){
        return x2;
    }
    
    public void setY1(int y1){
        this.y1 = (y1 >= 0 ? y1 :0);
    }
    
    public int getY1(){
        return y1;
    }
    
    public void setY2(int y2){
        this.y2 = (y2 >= 0 ? y2 :0);
    }
    
    public int getY2(){
        return y2;
    }
    
    public void setColor(Color color){
        myColor = color;
    }
    
    public Color getColor(){
        return myColor;
    }
    
    /**
     * Abstract method for drawing the shape that must be overriden
     */
    public abstract void draw(Graphics g);
}
