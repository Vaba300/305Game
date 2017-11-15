import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverButton extends Actor
{
    //creates instance of button
    public GameOverButton() 
    {
        //sets button image
        System.out.println("init");
        GreenfootImage contButton = new GreenfootImage("Continue.png");
        this.setImage(contButton);
    }   
    public void act()
    {
        //when button is clicked, everything resets from beginning 
        if(Greenfoot.mouseClicked(this))
        {
            System.out.println("init");
            Greenfoot.setWorld(new MyWorld());
        }
        
    }
}
