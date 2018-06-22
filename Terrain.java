import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Main terrain
 * 
 * @author Marco Ly
 * Help with terrain generation from Hyson Leung
 * @version 2.2
 */
public class Terrain extends World
{
    GreenfootSound ambient = new GreenfootSound ("[HD] [STEREO] War Ambient Sounds.mp3");

    /**
     * Constructor for the game terrain
     */
    public Terrain()
    {    
        super(1024, 768, 1); 
        addObject(new Tank1(), 45, 550);
        addObject(new Tank2() , 980, 550);
        for (int x = 0; x <= 40 ; x+=6) {
            for (int i = 0; i <= 1026 ; i+=6) {
                addObject(new Grass(), i, 665-x);
            }
        }
        for (int x = 0; x <= 12 ; x+=6) {
            for (int i = 0; i <= 1026; i+=6) {
                addObject(new Bedrock(), i, 670+x);
            }
        }

        int hillHeight = 200;
        int hillLength = 400;
        int hillX = 0+((768-hillLength/2)/2);

        for(double i = 0; i <= 180; i += hillLength/180){
            double radian = i*2*Math.PI/360;
            int round = (int) Math.round(hillHeight*Math.sin((1/(hillLength/360))*(radian)));
            while(round%6 != 0 && round <= 6){
                round++;
            }
            while(round%6 >= 3){
                round++;
            }
            while(round%6 < 3 && round%6 != 0){
                round--;
            }
            addObject(new Grass(), hillX, 635-round);
            for(int j = 635-round; 635 > j+6; j += 6){
                addObject(new Grass(), hillX, j);
            }
            hillX += 6;
        }
        ambient.setVolume(20);
        ambient.playLoop();
    }
    
    /**
     * Stops the background sound of Terrain
     */
    public void terminateMusic() {
        ambient.stop();
    }
}

