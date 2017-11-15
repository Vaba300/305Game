import greenfoot.*; 

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public MyWorld()
    {    
       super(600, 400, 1);
       //sets background image
       GreenfootImage startText = new GreenfootImage("startMenu.jpg");
       setBackground(startText);
       //instantiates start button and adds it to world
       Button button = new Button();
       addObject(button, 304, 266);
    }
    
}
