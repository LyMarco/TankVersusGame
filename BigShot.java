import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Larger explosion radius, explodes 2 seconds after landing
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class BigShot extends Projectile
{
    SimpleTimer fuseDelay;
    boolean fuseMarked;
    GreenfootSound explode2;

    /**
     * Constructor for Bigshot projectile
     */
    public BigShot (double startX, double startY, double Vx, double Vy){
        super(startX, startY, Vx, Vy);
        fuseDelay = new SimpleTimer();
        fuseMarked = false;
        explode2 = new GreenfootSound("exp 4.wav");
        explode2.setVolume(50);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (getX() >= 1023 || getX() <= 0) {getWorld().removeObject(this);
        }else if (fuseDelay.millisElapsed() >= 1000 && fuseMarked == true){explode(60,40, explode2);
        }else if (hasLanded() == true && fuseMarked == false){markFuse();
        }else if (hasLanded() == false) {setLocation(getX() + Vx, getY() + Vy);}
        Vy += gravity;
    }    

    /**
     * Marks the detonation fuse of the shot
     */
    public void markFuse() {
        fuseDelay.mark();
        fuseMarked = true;
    }
}
