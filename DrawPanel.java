/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class handles mouse events and uses them to draw shapes.
 * It has many variables for the current shape [type, variable to store shape object, color, fill].
 * It contains a JLabel called statusLabel for the mouse coordinates
 * It has methods for undoing and clearing shapes.
 * It has a private inner class MouseHandler which extends MouseAdapter and 
 * handles mouse and mouse motion events used for drawing the current shape.
 */
public class DrawPanel extends JPanel{
    private ArrayList<Shape> shapes;
    public enum ShapeType {Line, Oval, Rectangle};
    private ShapeType shapeType;
    public Shape currentShape;
    public Color currentColor;
    private boolean filledShape;
    private int shapeNumber = 0;
    JLabel statusLabel;
    DrawFrame parent;
    private int oldX, oldY, currentX, currentY, tmpX, tmpY;
    
    /**
     * This constructor sets the current shape variables to default values.
     * It initalizes statusLabel from JLabel passed in.
     * Sets up the panel and adds event handling for mouse events.
     */
    public DrawPanel(DrawFrame parent){
        this.parent = parent;
        shapes = new ArrayList<Shape>(10);
        statusLabel = new JLabel(); 
        statusLabel.setBackground(Color.GRAY);
        
        statusLabel.setForeground(Color.BLACK);
        
        setShapeType(ShapeType.Line);
        setFilledShape(false);
        currentShape = null;
        setBackground(Color.WHITE);
        
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }
    
    /**
     * Calls the draw method for the existing shapes.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for(int i=0; i < shapes.size(); i++){
            shapes.get(i).draw(g);
        }
        
        if(currentShape != null){
            currentShape.draw(g);
        }
    }
    
    //Mutator methods for ShapeType, ShapeColor and ShapeFilled
    
    public void setShapeType(ShapeType type){
        shapeType = type;
    }
    
    public void setCurrentColor(Color color){
        currentColor = color;
    }
    
    public void setFilledShape(boolean filled){
        filledShape = filled;
    }
    
    //clears the last shape drawn
    public void clearLastShape(){
        shapes.remove(shapes.size() - 1);
        repaint();
    }
    
    //clears the entire drawing
    public void clearDrawing(){
        shapes.clear();
        repaint();
    }
    
    /**
     * Private inner class that implements MouseAdapter and does event handling for mouse events.
     */
    private class MouseHandler extends MouseAdapter implements MouseMotionListener{
        /**
         * When mouse is pressed draw a shape object based on type, color and filled.
         * X1,Y1 & X2,Y2 coordinate for the drawn shape are both set to the same X & Y mouse position.
         */
        public void mousePressed(MouseEvent e){
            oldX = e.getX();
            oldY = e.getY();
            switch(shapeType){
                case Line: currentShape = new Line(e.getX(), e.getY(), 
                        e.getX(), e.getY(), currentColor);
                    break;
                case Oval: currentShape = new Oval(e.getX(), e.getY(), 
                        e.getX(), e.getY(), currentColor, filledShape);
                    break;
                case Rectangle: currentShape = new Rectangle(e.getX(), e.getY(), 
                        e.getX(), e.getY(), currentColor, filledShape);
                    break;
            }
            
        }
        
        /**
         * When mouse is released set currentShapeObject's x2 & y2 to mouse pos.
         * Then addFront currentShapeObject onto the myShapes dynamic Stack 
         * and set currentShapeObject to null [clearing current shape object since it has been drawn].
         * and calls repaint() to redraw panel.
         */
        public void mouseReleased(MouseEvent e){
            if(currentShape != null){
                        currentX = e.getX();
                        currentY = e.getY();
                        oldX = currentX;
                        oldY = currentY;
                        repaint();
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                
                shapes.add(currentShape);
                shapeNumber++;
                currentShape = null;
                repaint();
            }
            if(e.getButton()==MouseEvent.BUTTON3){
                System.out.println("right click");
                shapes.get(shapeNumber).setX1(currentX);
                shapes.get(shapeNumber).setY1(currentY);
                shapes.get(shapeNumber).setX2(oldX);
                shapes.get(shapeNumber).setY2(oldY);
            }
        }
        
        public void mouseDragged(MouseEvent e){
            if(currentShape != null){
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                parent.panelText.setText(("(X): " + e.getX() + " (Y): " + e.getY()));
                repaint();
            }
            if(e.getButton()==MouseEvent.BUTTON3){
                shapes.get(shapeNumber).setX1(tmpX);
                shapes.get(shapeNumber).setY1(tmpY);
            }
        }
        
         /**
         * This method gets the mouse pos when it is moving and sets it to statusLabel.
         */
        public void mouseMoved(MouseEvent e){
            parent.panelText.setText(("(X): " + e.getX() + " (Y): " + e.getY()));
        }
        
        /**
         * This method displays a message to the user that the mouse is outside 
         * of the drawing area and sets the message to statusLabel
         */
        public void mouseExited(MouseEvent e){
            parent.panelText.setText("Mouse outside access window");
        }
    }
}
