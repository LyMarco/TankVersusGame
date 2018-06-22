import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main menu 
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class MainMenu extends World
{
    GreenfootSound menu = new GreenfootSound("OST 1.mp3");
    
    /**
     * Constructor for MainMenu
     */
    public MainMenu()
    {   
        super(1024, 768, 1); 
        menu.playLoop();
        addObject(new StartGameButton(), 181, 667);
        addObject(new InstructionsButton(), 846 , 665);
    }

    /**
     * Stops the menu music
     */
    public void terminateMusic()
    {
         menu.stop();
    }
}
