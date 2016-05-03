/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_7188;

import static hw3_7188.DrawPanel.ShapeType.Line;
import static hw3_7188.DrawPanel.ShapeType.Oval;
import static hw3_7188.DrawPanel.ShapeType.Rectangle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * Provides the GUI and encapsulates the DrawPanel
 * It creates 2 buttons undo and clear.
 * It creates 2 combobox colors and shapes.
 * It creates 1 checkbox filled to select whether shape is filled or not.
 * Has two private inner classes 
 * One for handling button events
 * Another for handling both combo box events and checkbox events
 */
public class DrawFrame extends JFrame{
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    
    private JButton undo;
    private JButton clear;
    
    private JComboBox colorsBox;
    private JComboBox shapesBox;
    
    private JCheckBox filledbox;
    
    private JPanel selectionPanel;
    
    public static final String[] shapesToDraw = {"Line", "Oval", "Rectangle"};
    public static final String[] four = {"Black", "Blue", "Green", "Red"};
    public static final Color[] colors = {Color.BLACK, Color.BLUE, 
        Color.GREEN, Color.RED};
    
    protected JLabel panelText=new JLabel("Paint");
    
    private DrawPanel graphicsPanel;
    
    /**
     * This constructor sets the name of the JFrame.
     * It also creates a DrawPanel object that extends JPanel for drawing the shapes and contains
     * a statuslabel for mouse position.
     * Initializes widgets for buttons, comboboxes and checkbox
     * It also adds event handlers for the widgets
     */
    public DrawFrame(){
        createSelectionPanel();
        createGraphicsPanel();
        
        add(panelText, BorderLayout.SOUTH);
        this.setTitle("Paint");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void createSelectionPanel(){
        ClearButtonListener clearScreen = new ClearButtonListener();
        UndoButtonListener undoLast = new UndoButtonListener();
        ColorsBoxHandler colorChoice = new ColorsBoxHandler();
        ShapesBoxHandler shapeChoice = new ShapesBoxHandler();
        CheckBoxHandler checked = new CheckBoxHandler();
        selectionPanel = new JPanel();
        
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        
        colorsBox = new JComboBox(four);
        colorsBox.setMaximumRowCount(4);
        shapesBox = new JComboBox(shapesToDraw);
        shapesBox.setMaximumRowCount(3);
        
        filledbox = new JCheckBox("Filled");
        
        selectionPanel.add(undo);
        undo.addActionListener(undoLast);
        selectionPanel.add(clear);
        clear.addActionListener(clearScreen);
        selectionPanel.add(colorsBox);
        colorsBox.addItemListener(colorChoice);
        selectionPanel.add(shapesBox);
        shapesBox.addItemListener(shapeChoice);
        selectionPanel.add(filledbox);
        filledbox.addItemListener(checked);
        this.add(selectionPanel, BorderLayout.NORTH);
    }
    
    private void createGraphicsPanel(){
        graphicsPanel = new DrawPanel(this);
        add(graphicsPanel, BorderLayout.CENTER);
    }
    
    
    private class UndoButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()== undo){
                graphicsPanel.clearLastShape();
            }
        }
    }
    
    private class ClearButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()== clear){
                graphicsPanel.clearDrawing();
            }
        }
    }
    
    private class ColorsBoxHandler implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e){
            if(e.getItem() == "Black"){
                graphicsPanel.setCurrentColor(colors[0]);
            } else if(e.getItem() == "Blue"){
                graphicsPanel.setCurrentColor(colors[1]);
            } else if(e.getItem() == "Green"){
                graphicsPanel.setCurrentColor(colors[2]);
            } else if(e.getItem() == "Red"){
                graphicsPanel.setCurrentColor(colors[3]);
            }
        }
    }
    
    private class ShapesBoxHandler implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            if(e.getItem() == "Line"){
                graphicsPanel.setShapeType(Line);
            } else if(e.getItem() == "Oval"){
                graphicsPanel.setShapeType(Oval);
            } else if(e.getItem() == "Rectangle"){
                graphicsPanel.setShapeType(Rectangle);
            }
        }
    }
    
    private class CheckBoxHandler implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            if(e.getStateChange() == 1){
                graphicsPanel.setFilledShape(true);
            } else if(e.getStateChange() == 2){
                graphicsPanel.setFilledShape(false);
            }
        }
    }
    
    public static void main (String[] args){
        DrawFrame draw;
        draw = new DrawFrame();
    }
}
