import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Instructions screen
 * @author Marco Ly
 * @version 2.2
 */
public class Instructions extends World
{

    /**
     * Constructor for objects of class Instructions.
     */
    public Instructions()
    {    
        super(1024, 768, 1); 
        addObject(new Back(),512,706);
        addObject(new StartGameButton(),512,616);
    }
}
