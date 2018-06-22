import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
//import java.awt.Font;

/**
 * Counter that displays a number.
 * 
 * @author Michael Kolling
 * slightly altered by Marco Ly
 * @version 1.0.1
 */
public class Counter extends Actor
{
    private int value = 0;
    private int target = 0;
    private String text;
    private int stringLength;

    /**
     * Constructor for Counter
     */
    public Counter()
    {
        this("", 0);
    }

    /**
     * Constructor for Counter
     */
    public Counter(String prefix, int initialValue)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 16;

        setImage(new GreenfootImage(stringLength, 24));
        GreenfootImage image = getImage();
        Font font = image.getFont();
        image.setFont(font.deriveFont(24.0F));  // use larger font
        
        value = initialValue;
        target = initialValue;
        updateImage();
    }
    
     /**
     * Act - do whatever the Tank1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if(value < target) {
            value++;
            updateImage();
        }
        else if(value > target) {
            value--;
            updateImage();
        }
    }

    public void add(int score)
    {
        target += score;
    }

    public void subtract(int score)
    {
        target -= score;
    }

    public int getValue()
    {
        return value;
    }
        
    public void setValue(int value) {
        this.value = value;
        this.target = value;
        updateImage();
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 18);
    }
}
