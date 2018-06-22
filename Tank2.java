import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player 2 Tank
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Tank2 extends Tank
{
    /**
     * Constructor for Tank1.
     */
    public Tank2() {
        super();
    }

    /**
     * Creates all objects and marks timers associated with the object. Sets specific turret rotation.
     */
    public void addedToWorld(World Terrain) {
        turret = new Turret();
        Terrain.addObject(turret , getX(), getY() - 2);
        Terrain.addObject(healthCounter, 803, 708);
        Terrain.addObject(wepCoolCounter, 963, 708);
        Terrain.addObject(powerCounter, 795, 747);
        Terrain.addObject(ammoCount, 870,50);
        Terrain.addObject(wepDisplay, 915,20);
        turret.setRotation(180);
    }

    /**
     * Act - do whatever the Tank1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act("left", "right", "down", "up", "0", "4", "5", "2" , "1");
    }    
}
