import edu.digipen.Game;
import edu.digipen.GameLevel;

public class Main
{
    public static void main()
    {
        Game.initialize(800, // window width
                        600, // window height
                        60, // Frames Per Second
                        new ArenaLevel());
                        
        while(Game.getQuit() == false)
        {
            Game.update();
        }
        
        Game.destroy();
    }
}