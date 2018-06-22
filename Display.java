import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Font;

/**
 * Display for a name and sub-name
 * 
 * @author Marco Ly
 * @version 2.2
 */
public class Display extends Actor
{
    int stringLength;
    String displayName;
    
    /**
     * Constructer for a display 
     */
    public Display(String name, String subName) {
        displayName = name;
        //         stringLength = (weapon.length() + 2) * 16;
        stringLength = 220;

        setImage(new GreenfootImage(stringLength, 24));
        GreenfootImage image = getImage();
        Font font = image.getFont();
        image.setFont(font.deriveFont(24.0F));  // use larger font
        
        updateImage(subName);
    }

    /**
     * Updates the sub name for the display
     */
    public void updateImage(String name) {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(this.displayName + name, 1, 18);
    }
}
