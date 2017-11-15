import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {    
        super(600, 400, 1);
        //sets image for game over screen
        GreenfootImage gameover = new GreenfootImage("gameover.png");
        setBackground(gameover);
        //instantiates game over button and adds it to world
        GameOverButton contButton = new GameOverButton();
        addObject(contButton, 304, 330);
    }
}
