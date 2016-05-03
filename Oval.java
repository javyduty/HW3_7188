/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from BoundedShape and is responsible for drawing an oval.
 */
public class Oval extends BoundedShape{
    
    public Oval(){
        super();
    }
    
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean filled){
        super(x1, y1, x2, y2, color, filled);
    }
    
    public void draw(Graphics g){
        g.setColor(getColor());
        if(getFilled()){
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
}
