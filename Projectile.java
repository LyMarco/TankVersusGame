import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * Projectiles
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Projectile extends Actor
{
    int width = getImage().getWidth();
    int height = getImage().getHeight();
    int radius = getImage().getWidth()/2;
    int gravity = 1;
    int Vx;
    int Vy;
    int startX;
    int startY;
    Random randInt = new Random();
    GreenfootSound explode1;

    /**
     * Constructor for projectiles
     */
    public Projectile(double startX, double startY, double Vx, double Vy) {
        this.startX = (int)startX ;
        this.startY = (int)startY;
        this.Vx = (int)Vx;
        this.Vy = (int)Vy;
        explode1 = new GreenfootSound("exp 2.wav");
        explode1.setVolume(60);
    }

    /**
     * Sets starting location of the projectile
     */
    public void addedToWorld(World Terrain) {
        setLocation(startX, startY);
    }

    /**
     * Act - do whatever the Tank1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (getX() >= 1023 || getX() <= 0) {getWorld().removeObject(this); 
        }else if (hasLanded()){explode(20,20);
        }else if (hasLanded() == false) {setLocation(getX() + Vx, getY() + Vy);}
        Vy += gravity;
    }

    /**
     * Checks if there is an object from the projectile to the vertical and horizontal velocity. If there is, snaps to that object and lands.
     */
    public boolean hasLanded () {
        for (int i = 0; i <= height/2 + Vy; i++) {
            for (int h = 0; h <= Vx; h++) {           
                if (getOneObjectAtOffset(h, i, Tile.class) != null || getOneObjectAtOffset(h, i, Bedrock.class) != null || getOneObjectAtOffset(h,i, Tank.class) != null) {
                    setLocation(getOneObjectAtOffset(h,i,null).getX(), getOneObjectAtOffset(h,i,null).getY());
                    return true;
                }
            }
            for (int h = 0; h >= Vx; h--) {           
                if (getOneObjectAtOffset(h, i, Tile.class) != null || getOneObjectAtOffset(h, i, Bedrock.class) != null || getOneObjectAtOffset(h,i, Tank.class) != null) {
                    setLocation(getOneObjectAtOffset(h,i,null).getX(), getOneObjectAtOffset(h,i,null).getY());
                    return true;
                }
            }
        } 
        for (int i = 0; i >= height/2 + Vy; i--) {
            for (int h = 0; h <= Vx; h++) {           
                if (getOneObjectAtOffset(h, i, Tile.class) != null || getOneObjectAtOffset(h, i, Bedrock.class) != null || getOneObjectAtOffset(h,i, Tank.class) != null) {
                    setLocation(getOneObjectAtOffset(h,i,null).getX(), getOneObjectAtOffset(h,i,null).getY());
                    return true;
                }
            }
            for (int h = 0; h >= Vx; h--) {           
                if (getOneObjectAtOffset(h, i, Tile.class) != null || getOneObjectAtOffset(h, i, Bedrock.class) != null || getOneObjectAtOffset(h,i, Tank.class) != null) {
                    setLocation(getOneObjectAtOffset(h,i,null).getX(), getOneObjectAtOffset(h,i,null).getY());
                    return true;
                }
            }
        } 
        return false; 
    }

    /**
     * Removes object, removing all tiles in range of blastRadius, deals damage to any tanks within damageRadius.
     */
    public void explode(int blastRadius, int damageRadius) {
        List tilesList = getObjectsInRange(blastRadius, Tile.class);
        for (int i = 0; i < tilesList.size(); i++){getWorld().removeObject((Actor)tilesList.get(i));}
        List tanksList = getObjectsInRange(damageRadius, Tank.class);
        for (int i = 0; i < tanksList.size(); i++){
            Tank tempTank = (Tank) tanksList.get(i);
            tempTank.hit(tempTank.getX() - this.getX(), tempTank.getY() - this.getY(), damageRadius);
        }
        explode1.play();
        getWorld().removeObject(this);
    }

        public void explode(int blastRadius, int damageRadius, GreenfootSound explode2) {
        List tilesList = getObjectsInRange(blastRadius, Tile.class);
        for (int i = 0; i < tilesList.size(); i++){getWorld().removeObject((Actor)tilesList.get(i));}
        List tanksList = getObjectsInRange(damageRadius, Tank.class);
        for (int i = 0; i < tanksList.size(); i++){
            Tank tempTank = (Tank) tanksList.get(i);
            tempTank.hit(tempTank.getX() - this.getX(), tempTank.getY() - this.getY(), damageRadius);
        }
        explode2.play();
        getWorld().removeObject(this);
    }
    
    /**
     * Removes the object if it is out of the bounds of the terrain
     */
    public void outOfBounds() { 
        if(getX() >= 1023 || getX() <= 0) {
            getWorld().removeObject(this);
        }
    }
}
