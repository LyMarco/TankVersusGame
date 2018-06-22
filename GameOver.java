import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * End screen
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class GameOver extends World
{    
    /**
     * Constructor for GameOver
     */
    public GameOver()
    {   
        super(1024, 768, 1); 
        addObject(new ReplayButton(), 787, 473);
        addObject(new Back(), 243, 478);
    }
}
