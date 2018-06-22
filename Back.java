import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Goes back to Menu
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Back extends Button
{
    boolean exists = false;
    /**
     * Act - do whatever the InstructionsButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseMoved(this)) {
            setImage("Back 2.png");
        } else if (Greenfoot.mouseMoved(getWorld())){
            setImage("Back 1.png");
        }
        if (Greenfoot.getMouseInfo()!=null)
        {
            if (Greenfoot.getMouseInfo().getActor() == this && Greenfoot.getMouseInfo().getButton() == 1)
            {
                MainMenu menu = new MainMenu();
                Greenfoot.setWorld(menu);
            }
        }
    }    
}
