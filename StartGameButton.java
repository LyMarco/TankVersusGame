import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Starts the game
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class StartGameButton extends Button
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
            setImage("Start 2.png");
        } else if (Greenfoot.mouseMoved(getWorld())){
            setImage("Start 1.png");
        }
        if (exists)
        {
            if (Greenfoot.getMouseInfo()!=null)
            {
                if (Greenfoot.getMouseInfo().getActor() == this && Greenfoot.getMouseInfo().getButton() == 1)
                {
                    Terrain world = new Terrain();
//                     if (getWorld() == (World)new MainMenu()){
                        MainMenu menu = (MainMenu)getWorld();
                        menu.terminateMusic();
                        load.setVolume(50);
                        load.play();
//                     }
                    Greenfoot.setWorld(world);
                }
            }
        }
    }    
}
