import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Turret for the tanks
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Turret extends Actor
{
    int width = getImage().getWidth();
    int height = getImage().getHeight();
    
    /**
     * Returns the rotation of the turret in radians
     */
    public double getRadRotation() {
        return Math.toRadians(getRotation());
    }
    
    /**
     * Returns the length of the turret
     */
    public int getLength() {
        return width/2;
    }
}
