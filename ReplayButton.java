import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Starts the game over
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class ReplayButton extends Button
{
    boolean exists = false;
    GreenfootSound load = new GreenfootSound("Load in.wav");
    /**
     * Constructor for StartGameButton
     */
    public void addedToWorld(World MainMenu) {
        exists = true;
    }

    /**
     * Act - do whatever the Tank1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseMoved(this)) {
            setImage("Replay 2.png");
        } else if (Greenfoot.mouseMoved(getWorld())){
            setImage("Replay 1.png");
        }
        if (exists)
        {
            if (Greenfoot.getMouseInfo()!=null)
            {
                if (Greenfoot.getMouseInfo().getActor() == this && Greenfoot.getMouseInfo().getButton() == 1)
                {
                    Terrain world = new Terrain();
                    Greenfoot.setWorld(world);
                    load.setVolume(50);
                    load.play();
                }
            }
        }
    }    
}
