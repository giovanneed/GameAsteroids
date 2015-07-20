import edu.digipen.GameObject;
import edu.digipen.Graphics;
import edu.digipen.math.PFRandom;

public class GameObjectUtils
{
    public static void Wrapping(GameObject obj, float xOffset, float yOffset)
    {
        float x  = obj.getPositionX();
        float y = obj.getPositionY();
        //wrap x along horizontal
        //wrap y along vertical
               
        
        float xMin = -Graphics.getWindowWidth() / 2.f;
        float xMax = Graphics.getWindowWidth() / 2.f;
        
        float yMin = -Graphics.getWindowHeight() / 2.f;
        float yMax = Graphics.getWindowHeight() / 2.f;

        
        if(x< xMin - xOffset)
        {
           x = xMax + xOffset;
        }
        if(x> xMax + xOffset)
        {
           x = xMin - xOffset;
        }
        
        
        
        if(y< yMin - yOffset)
        {
           y = yMax + yOffset;
        }
        if(y> yMax + yOffset)
        {
           y = yMin - yOffset;
        }
        
        obj.setPosition(x,y);
    }
    
 
}
