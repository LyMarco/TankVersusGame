import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shower projectile spreads into 5 different shots with deviated horizontal velocities
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Shower extends Projectile
{
    boolean hasSpread = false;
    SimpleTimer fuseDelay;
    Shot[] shotArray = new Shot[5];
    
    /**
     * Constructor for Shower
     */
    public Shower (double startX, double startY, double Vx, double Vy){
        super(startX, startY, Vx, Vy);
        fuseDelay = new SimpleTimer();
        fuseDelay.mark();
    }

    /**
     * Act - do whatever the Shot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        spread();
    }    
    
    /**
     * Creates more shots with varying horizontal velocities, branching from the original shower shot
     */
    public void spread(){
        if (fuseDelay.millisElapsed() >= 400 && hasSpread == false) {
            int vDeviation = 0;
            int lDeviation = 0;
            for(Shot shots: shotArray) {
                shots = new Shot(this.getX()+lDeviation, this.getY(), Vx + vDeviation, Vy);
                if (Vx > 0){vDeviation+=1; lDeviation+=5;}
                else if (Vx < 0){vDeviation-=1; lDeviation-=5;}
                getWorld().addObject(shots,0,0);
            }
            hasSpread = true;
        }
    }
}
