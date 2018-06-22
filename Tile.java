import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Base tiles for terrain
 * 
 * @author Marco Ly
 * help with finding bugs by Hyson Leung
 * @version 2.2
 */
public class Tile extends CollisionObject
{
    int width = getImage().getWidth();
    int height = getImage().getHeight();
    int gravity = 5;
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (isFalling()) {
            setLocation(getX(), getY() + gravity) ;
        }
    }
    
     /**
     * Checks if there is nothing between the bottom of the object and the downward velocity of the object. 
     * If there is, snap the object to rest directly above the intersecting object.
     */
    public boolean isFalling () {
        for (int i = height/2; i <= height/2 + gravity; i++) {
            if (getOneObjectAtOffset(0, i, null) != null && getOneObjectAtOffset(0, i, Counter.class) == null && getOneObjectAtOffset(0, i, Display.class) == null) {
                setLocation(getX(), getY() + i - height/2);
                return false;
            }
        }
        return true; 
    }
}