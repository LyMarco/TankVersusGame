import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Tank here.
 * 
 * @author Marco Ly
 * Help with finding bugs and debugging by James Ly and Hyson Leung
 * @version 2.2
 */
public class Tank extends CollisionObject
{
    int[] ammoArray;
    int ammoNumber;
    int width;
    int height;
    int gravity;
    int speed;
    int power;

    int wepCoolTime;
    int wepCoolTimer;
    int wepSelection;

    Turret turret;

    Counter healthCounter;
    Counter wepCoolCounter;
    Counter powerCounter;
    Counter ammoCount;

    SimpleTimer wepCooldown;
    SimpleTimer powerDelay;
    SimpleTimer wepSelectDelay;

    Display wepDisplay;

    GreenfootSound reload = new GreenfootSound("reload.wav");
    GreenfootSound fire1 = new GreenfootSound("Shots Fired.wav");
    GreenfootSound move = new GreenfootSound("Tank moving sound effect 1.wav");
    GreenfootSound explode = new GreenfootSound("exp 3.wav");
    /**
     * Constructor for class Tank
     */
    public Tank() {
        ammoNumber = 6;
        ammoArray = new int [ammoNumber];
        fillAmmo();
        width = getImage().getWidth();
        height = getImage().getHeight();
        gravity = 5;
        speed = 1;
        power = 25;
        wepSelection = 0;

        turret = new Turret();

        wepCoolTime = 4;

        healthCounter = new Counter("Health: ", 300);
        wepCoolCounter = new Counter("Cooldown: ", 0);
        powerCounter = new  Counter("Power: ", power);
        ammoCount = new Counter("Ammo: ", ammoArray[wepSelection]);

        wepCooldown = new SimpleTimer();
        powerDelay = new SimpleTimer();
        wepSelectDelay = new SimpleTimer();

        wepDisplay = new Display("Weapon: ", "Shot");

        fire1.setVolume(50);
        reload.setVolume(70);
        move.setVolume(50); 
    }

    /**
     * Creates all objects linked with the tank
     */
    public void addedToWorld(World Terrain) {
        Terrain.addObject(turret , getX(), getY() - 3);
        Terrain.addObject(healthCounter, 84, 708);
        Terrain.addObject(wepCoolCounter, 247, 708);
        Terrain.addObject(powerCounter, 78, 747);
        Terrain.addObject(ammoCount, 70,50);
        Terrain.addObject(wepDisplay, 115,20);
        wepCooldown.mark();
        powerDelay.mark();
    }

    /**
     * Act - do whatever the Tank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(String leftKey, String rightKey, String upKey, String downKey, String fireKey, String powerDown, String powerUp, 
    String wepSelectUp, String wepSelectDown) 
    {
        if (isFalling()) {
            this.setLocation(getX(), getY() + gravity);
        }
        if (isFalling() == false) {
            if (Greenfoot.isKeyDown(fireKey) && wepCooldown.millisElapsed()/1000 >= wepCoolTime){
                wepCoolTime = 4;
                shootWeapons();
            }
            setMovement(leftKey, rightKey);
            setTurretRotation(upKey, downKey);
            setPower(powerDown, powerUp);
        }
        turret.setLocation(this.getX(), this.getY() - 2);
        setWeapon(wepSelectDown,wepSelectUp);

        updateWepDisplay();
        updatePowerCounter();
        updateAmmo();

        checkWepCooldown();

        List tanksList = getWorld().getObjects(Tank.class);
        if (tanksList.size() <=1) {
            GameOver world = new GameOver();
            Terrain terrain = (Terrain)getWorld();
            terrain.terminateMusic();
            Greenfoot.setWorld(world);
        }
        checkHealth();
    }

    /**
     * Checks if there is nothing between the bottom of the object and the downward velocity of the object. 
     * If there is, snap the object to rest directly above the intersecting object.
     */
    public boolean isFalling () {
        for (int h = height/2; h <= height/2 + gravity; h++) {
            for (int i = width/2; i >= -width/2; i--) {
                if (getOneObjectAtOffset(i, h, CollisionObject.class) != null) {
                    setLocation(getX(), getY() + h - height/2 );
                    return false;
                }
            }
        }
        return true; 
    }

    /**
     * Checks if there is nothing between the right side of the object and the horizontal velocity of the object. 
     * If there is, snap the object to rest directly beside the object.
     * If the interfering object is less than half the object's height, decrease the Y position to climb it.
     */
    public boolean canGoRight() {
        for (int i = width/2; i <= width/2 + speed; i++) {
            if (getOneObjectAtOffset(i, -1, CollisionObject.class) != null) {
                setLocation(getX() + i - width/2, getY());
                return false;
            }
        }
        while (getOneObjectAtOffset(width/2, height/2-1, CollisionObject.class) != null) {
            setLocation(getX(),getY()-1);
        }
        return true;
    }

    /**
     * Checks if there is nothing between the left side of the object and the horizontal velocity of the object. 
     * If there is, snap the object to rest directly beside the object.
     * If the interfering object is less than half the object's height, decrease the Y position to climb it.
     */
    public boolean canGoLeft() {
        for (int i = -width/2; i >= -width/2 - speed; i--) {
            if (getOneObjectAtOffset(i, -1, CollisionObject.class) != null) {
                setLocation(getX() + i + width/2, getY());
                return false;
            }
        }
        while (getOneObjectAtOffset(-width/2, height/2-1, CollisionObject.class) != null) {
            setLocation(getX(),getY()-1);
        }
        return true;
    }

    /** 
     * Adds ammunition to all ammo holds of each weapon
     */
    public void fillAmmo() {
        ammoArray[0] = 999;
        ammoArray[1] = 10;
        ammoArray[2] = 10;
        ammoArray[3] = 15;
        ammoArray[4] = 20;
        ammoArray[5] = 5;
    }

    /**
     * Sets movement of the object according to canGoLeft() and canGoRight(). 
     */
    public void setMovement(String leftKey, String rightKey) {
        if(Greenfoot.isKeyDown(leftKey) && canGoLeft()) {
            setLocation(getX() -speed, getY());
            if (!move.isPlaying())
            {
                move.play();
            }
        }
        if(Greenfoot.isKeyDown(rightKey) && canGoRight()) {
            setLocation(getX() + speed, getY()); 
            if (!move.isPlaying())
            {
                move.play();
            }
        }
        if (!Greenfoot.isKeyDown(rightKey)&&!Greenfoot.isKeyDown(leftKey))
        {
            if (move.isPlaying())
            {
                move.stop();
            }
        }
    }

    /**
     * Increases or decreases the value of power if powerUp and powerDown keys are pressed.
     */
    public void setPower(String down, String up) {
        if (Greenfoot.isKeyDown(down) && power > 0 && powerDelay.millisElapsed() >= 400) {
            power--;
            powerDelay.mark();
        } else if (Greenfoot.isKeyDown(up) && power < 50 && powerDelay.millisElapsed() >= 400) {
            power++;
            powerDelay.mark();
        }
    }

    /**
     * Increases or decreases the rotation angle of turret. Limits the rotation of the turret to be parallel to the ground.
     */
    public void setTurretRotation(String upKey, String downKey) {
        if(turret.getRotation() > 180 && turret.getRotation() <= 359 || turret.getRotation() == 0) {
            if(Greenfoot.isKeyDown(upKey)) {
                turret.turn(-1);
            }
        }
        if(turret.getRotation() >= 180 && turret.getRotation() <= 359) {
            if(Greenfoot.isKeyDown(downKey)){
                turret.turn(1);
            }
        }
    }

    /**
     * Sets the weapon selection by increasing or decreasing the value of wepSelection. 
     * Decreasing the value past the minimum value will set the value to the maximum and vica versa.
     */
    public void setWeapon (String down, String up) {
        if (Greenfoot.isKeyDown(down) && wepSelectDelay.millisElapsed() >= 400) {
            if (wepSelection <= 0) {
                wepSelection = ammoNumber-1;
                wepSelectDelay.mark();
            } else {
                wepSelection--;
                wepSelectDelay.mark();
            }
        } else if (Greenfoot.isKeyDown(up) && wepSelectDelay.millisElapsed() >= 400) {
            if (wepSelection >= ammoNumber-1) {
                wepSelection = 0;
                wepSelectDelay.mark();
            } else {
                wepSelection++;
                wepSelectDelay.mark();
            }
        }
    }

    /**
     * Updates the value for the power counter display/
     */
    public void updatePowerCounter() {
        powerCounter.setValue(power);
    }

    /**
     * Updates the value for the weapon display/
     */
    public void updateWepDisplay() {
        if (wepSelection == 0) {
            wepDisplay.updateImage("Shot");
        } else if (wepSelection == 1) {
            wepDisplay.updateImage("Big Shot");
        } else if (wepSelection == 2) {
            wepDisplay.updateImage("Shower");
        } else if (wepSelection == 3) {
            wepDisplay.updateImage("Scatter");
        }else if (wepSelection == 4) {
            wepDisplay.updateImage("Rapid Fire");
        }else if (wepSelection == 5) {
            wepDisplay.updateImage("Bury");
        }
    }

    /**
     * Updates the ammo value of the current weapon.
     */
    public void updateAmmo() {
        ammoCount.setValue(ammoArray[wepSelection]);
    }

    /**
     * Checks that the health is still above 0. If not, it explodes the tank.
     */
    public void checkHealth() {
        if  (healthCounter.getValue() <= 0) {
            explode();
            getWorld().removeObject(turret);
            getWorld().removeObject(this);
            healthCounter.setValue(0);
        }
    }

    /**
     * Checks and updates the weapon cooldown time based on the value of wepCoolTime
     */
    public void checkWepCooldown() {
        if(wepCoolCounter.getValue() > 0 && wepCooldown.millisElapsed()/1000 <= wepCoolTime) {
            wepCoolCounter.setValue(wepCoolTime - wepCooldown.millisElapsed()/1000);
        }
    }

    /**
     * Shoots a Shot projectile.
     */
    public void shoot() {
        Shot shot = new Shot(turret.getX() + turret.getLength()*Math.cos(turret.getRadRotation()), 
                turret.getY() + turret.getLength()*Math.sin(turret.getRadRotation()),
                power*Math.cos(turret.getRadRotation()),
                power*Math.sin(turret.getRadRotation()));
        getWorld().addObject(shot, 200, 200);
    }

    /**
     * Shoots a Shower projectile.
     */
    public void shootShower() {
        Shower shower = new Shower(turret.getX() + turret.getLength()*Math.cos(turret.getRadRotation()), 
                turret.getY() + turret.getLength()*Math.sin(turret.getRadRotation()),
                power*Math.cos(turret.getRadRotation()),
                power*Math.sin(turret.getRadRotation()));
        getWorld().addObject(shower, 0, 0);
    }

    /**
     * Shoots a Scatter projectile.
     */
    public void shootScatter() {
        Scatter scatter = new Scatter (turret.getX() + turret.getLength()*Math.cos(turret.getRadRotation()), 
                turret.getY() + turret.getLength()*Math.sin(turret.getRadRotation()),
                power*Math.cos(turret.getRadRotation()),
                power*Math.sin(turret.getRadRotation()));
        getWorld().addObject(scatter, 0, 0);
    }

    /**
     * Shoots a BigShot projectile.
     */
    public void shootBigShot() {
        BigShot bigshot = new BigShot (turret.getX() + turret.getLength()*Math.cos(turret.getRadRotation()), 
                turret.getY() + turret.getLength()*Math.sin(turret.getRadRotation()),
                power*Math.cos(turret.getRadRotation()),
                power*Math.sin(turret.getRadRotation()));
        getWorld().addObject(bigshot, 0, 0);
    }

    /**
     * Shoots a RapidShot projectile.
     */
    public void shootRapidShot() {
        RapidShot rapid = new RapidShot(turret.getX() + turret.getLength()*Math.cos(turret.getRadRotation()), 
                turret.getY() + turret.getLength()*Math.sin(turret.getRadRotation()),
                power*Math.cos(turret.getRadRotation()),
                power*Math.sin(turret.getRadRotation()));
        getWorld().addObject(rapid, 200, 200);
    }

    /**
     * Shoots a Bury projectile.
     */
    public void shootBury() {
        Bury bury = new Bury (turret.getX() + turret.getLength()*Math.cos(turret.getRadRotation()), 
                turret.getY() + turret.getLength()*Math.sin(turret.getRadRotation()),
                power*Math.cos(turret.getRadRotation()),
                power*Math.sin(turret.getRadRotation()));
        getWorld().addObject(bury, 0, 0);
    }

    /**
     * Shoot the currently selected weapon.
     */
    public void shootWeapons() {
        if (wepSelection == 0 && ammoArray[wepSelection] != 0) {
            shoot(); ammoArray[wepSelection]--; wepCooldown.mark(); wepCoolCounter.setValue(wepCoolTime);
            fire1.play();
            reload.play();
        } else if (wepSelection == 1 && ammoArray[wepSelection] != 0) {
            shootBigShot(); ammoArray[wepSelection]--; wepCooldown.mark(); wepCoolCounter.setValue(wepCoolTime);
            fire1.play();
            reload.play();
        } else if (wepSelection == 2 && ammoArray[wepSelection] != 0) {
            shootShower(); ammoArray[wepSelection]--; wepCooldown.mark(); wepCoolCounter.setValue(wepCoolTime);
            fire1.play();
            reload.play();
        } else if (wepSelection == 3 && ammoArray[wepSelection] != 0) {
            shootScatter(); ammoArray[wepSelection]--; wepCooldown.mark(); wepCoolCounter.setValue(wepCoolTime);
            fire1.play();
            reload.play();
        }else if (wepSelection == 4 && ammoArray[wepSelection] != 0) {
            shootRapidShot(); ammoArray[wepSelection]--; wepCooldown.mark(); wepCoolTime = 1; wepCoolCounter.setValue(wepCoolTime);
        } else if (wepSelection == 5 && ammoArray[wepSelection] != 0) {
            shootBury(); ammoArray[wepSelection]--; wepCooldown.mark(); wepCoolTime = 5; wepCoolCounter.setValue(wepCoolTime);
            fire1.play();
            reload.play();
        }
    }

    /**
     * Deals damage to the tank from projectile explosion. The damage is proportional to the distance between the tank and projectile upon explosion.
     */
    public void hit (int Dx, int Dy, int damageRadius) {
        int damageFactor = damageRadius - (int)Math.sqrt(Math.pow(Dx, 2) + Math.pow(Dy, 2)); 
        healthCounter.subtract(damageFactor);
    }

    /**
     * Explodes the tank
     */
    public void explode() {
        explode.play();
        List tilesList = getObjectsInRange(40, Tile.class);
        for (int i = 0; i < tilesList.size(); i++){getWorld().removeObject((Actor)tilesList.get(i));}
    }
}
