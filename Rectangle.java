/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from BoundedShape and is responsible for drawing a rectangle.
 */
public class Rectangle extends BoundedShape{
    
    public Rectangle(){
        super();
    }
    
    public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean filled){
        super(x1, y1, x2, y2, color, filled);
    }
    
    public void draw(Graphics g){
        g.setColor(getColor());
        if(getFilled()){
            g.fillRect(Math.min(x1, x2), Math.min(y1, y2),
                    Math.abs(x1 - x2), Math.abs(y1 - y2));
        }
            g.drawRect(Math.min(x1, x2), Math.min(y1, y2),
                    Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
