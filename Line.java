/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from Shape and is responsible for drawing a line.
 */
public class Line extends Shape {
    
    public Line(){
        super();
    }
    
    public Line(int x1, int y1, int x2, int y2, Color color){
        super(x1, y1, x2, y2, color);
    }
    
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawLine(x1,y1,x2,y2);
    }
}
