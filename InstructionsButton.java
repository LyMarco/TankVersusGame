import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button that goes to the Instructions screen
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class InstructionsButton extends Button
{
    boolean exists = false;
    /**
     * Act - do whatever the InstructionsButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseMoved(this)) {
            setImage("Ctrl 2.png");
        } else if (Greenfoot.mouseMoved(getWorld())){
            setImage("Ctrl 1.png");
        }
        if (Greenfoot.getMouseInfo()!=null)
        {
            if (Greenfoot.getMouseInfo().getActor() == this && Greenfoot.getMouseInfo().getButton() == 1)
            {
                Instructions ctrl = new Instructions();
                MainMenu menu = (MainMenu)getWorld();
                menu.terminateMusic();
                Greenfoot.setWorld(ctrl);
            }
        }
    }    
}
