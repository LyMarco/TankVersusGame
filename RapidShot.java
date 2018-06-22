import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular shot projectile, but intended for a lower cooldown delay
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class RapidShot extends Projectile
{
    GreenfootSound fire1 = new GreenfootSound("Shots Fired.wav");
    
    /**
     * Constructor for Rapid fire shots
     */
    public RapidShot (double startX, double startY, double Vx, double Vy){
        super(startX, startY, Vx, Vy);
        fire1.play();
    }

    /**
     * Act - do whatever the Shot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
}
