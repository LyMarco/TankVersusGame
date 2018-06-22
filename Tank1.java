import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player 1 Tank
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Tank1 extends Tank
{
    /**
     * Constructor for Tank1.
     */
    public Tank1() {
        super();
    }

    /**
     * Act - do whatever the Tank1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act("a", "d", "w", "s", "g", "y", "u", "j", "h");
    }    
}
