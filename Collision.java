import edu.digipen.GameObject;
import edu.digipen.Graphics;
import java.util.ArrayList;
import edu.digipen.math.Vec2;

public class Collision
{
 
    public Collision()
    {
        // initialise instance variables

    }
    
    public static GameObject getGameObjectCollided(GameObject firstObj,ArrayList<GameObject> list)
    {
      
         for (GameObject obj : list) {
            
            if(obj.getName().equals("Asteroid")){
                Asteroid asteroid = (Asteroid)obj;
                boolean r  = checkRectangleToRectangle(firstObj.getPosition(), 48,  48, obj.getPosition(),asteroid.size/2,asteroid.size/2);
                if (r ==  true) return obj;
            }
            
        }
        return null;
    }
    public static boolean CheckCollisionByList(GameObject firstObj,ArrayList<GameObject> list)
    {
    
        /*for (GameObject obj : list) {
            
            // boolean c = checkCircleToCircle(obj1.getPosition(),32, obj.getPosition(),32);
            // return checkRectangleToRectangle(obj1.getPosition(),obj1.getWidth()/2,obj1.getHeight()/2,obj.getPosition(),obj.getWidth()/2,obj.getHeight()/2);
            boolean r  = checkRectangleToRectangle(firstObj.getPosition(), 48,  48, obj.getPosition(),64,64);
            if (r ==  true) return true;
        }*/

        /*for(int i=0; i<list.size(); i++)
        {
            System.out.printf("Array de asteroids? numero " + i);
            GameObject obj = list
        }*/
        
        
         for (GameObject obj : list) {
            
            if(obj.getName().equals("Asteroid")){
                Asteroid asteroid = (Asteroid)obj;
                boolean r  = checkRectangleToRectangle(firstObj.getPosition(), 48,  48, obj.getPosition(),asteroid.size/2,asteroid.size/2);
                if (r ==  true) return true;
            }
            
        }

        return false;
    
    }
    
    public static boolean checkCircleToCircle(Vec2 c1Position, float c1Radius, Vec2 c2Position, float c2Radius)
    {
        float dx = c2Position.getX() - c1Position.getX();
        float dy = c2Position.getY() - c1Position.getY();
        
        float distanceSaquared = (dx*dx)+(dy*dy);


        float totalRadius = c1Radius - c2Radius;
        System.out.printf("\n totalRadius * totalRadius %f  distanceSaquared %f",totalRadius * totalRadius,distanceSaquared);
        if(totalRadius * totalRadius <= distanceSaquared ) return false;
        return true;
    }
    
    
     public static boolean checkRectangleToRectangle(Vec2 r1Position, float r1HalfWidth,  float r1HalfHeight, Vec2 r2Position, float r2HalfWidth,  float r2HalfHeight)
    {

        if(r1Position.getX() + r1HalfWidth < r2Position.getX() - r2HalfWidth)
        {
            return false;
        }
        
        if(r1Position.getX() - r1HalfWidth > r2Position.getX() + r2HalfWidth)
        {
            return false;
        }
        
        if(r1Position.getY() - r1HalfWidth > r2Position.getY() + r2HalfWidth)
        {
            return false;
        }
        
        if(r1Position.getY() + r1HalfWidth < r2Position.getY() - r2HalfWidth)
        {
            return false;
        }
        
        return true;
    }
    
}
