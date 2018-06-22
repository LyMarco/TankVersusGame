import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Scatters 5 shots when it lands
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Scatter extends Projectile
{
    boolean hasScattered = false;
    Shot[] shotArray = new Shot[5];
    Random r = new Random();
    
    /**
     * Constructor for Scatter projectile
     */
    public Scatter (double startX, double startY, double Vx, double Vy){
        super(startX, startY, Vx, Vy);
    }

    /**
     * Act - do whatever the Scatter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (getX() >= 1023 || getX() <= 0) {getWorld().removeObject(this); 
        }else if (hasLanded() && hasScattered == false){scatter();
        }else if (hasLanded() && hasScattered == true) {explode(20,20);
        }else if (hasLanded() == false) {setLocation(getX() + Vx, getY() + Vy);}
        Vy += gravity;
    }

    /**
     * Scatters new shots upward with random vertical and horizontal velocities
     */
    public void scatter() {
        for(Shot shots: shotArray) {
            Vy = r.nextInt(15) - 15;
            Vx = r.nextInt(15) + -7;
            shots = new Shot(this.getX(), this.getY(), Vx, Vy);
            getWorld().addObject(shots,0,0);
        }
        hasScattered = true;
    }
}
