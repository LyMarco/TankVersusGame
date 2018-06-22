import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Creates a dirt pile after 1.5 seconds of being shot
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Bury extends Projectile
{
    boolean hasDropped = false;
    SimpleTimer fuseDelay;

    /**
     * Constructor for Bury projectile
     */
    public Bury (double startX, double startY, double Vx, double Vy){
        super(startX, startY, Vx, Vy);
        fuseDelay = new SimpleTimer();
        fuseDelay.mark();
    }

    /**
     * Act - do whatever the Bury wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(fuseDelay.millisElapsed() >= 1500 && hasDropped == false) {drop();}
    }

    /**
     * Creates dirt pile
     */
    public void drop() {
        for (int i = getX() - 54; i <= getX() + 54; i+=6) {
            for (int j = 0; j <= 66; j+=6) {
                getWorld().addObject(new Dirt(), i, getY() - j);
            }
        }
        hasDropped = true;
        getWorld().removeObject(this);
    }
}
