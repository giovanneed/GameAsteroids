import edu.digipen.math.Vec2;


public class CollisionsCheck
{
    // instance variables - replace the example below with your own
    public static boolean checkCircleToCircle(Vec2 c1Position, float c1Radius, Vec2 c2Position, float c2Radius)
    {
        float dx = c2Position.getX() - c1Position.getX();
        float dy = c2Position.getY() - c1Position.getY();
        
        float distanceSaquared = (dx*dx)+(dy*dy);

       // System.out.printf("distanceSaquared %f",distanceSaquared);
        float totalRadius = c1Radius - c2Radius;
        
        if(totalRadius * totalRadius <= distanceSaquared ) return false;
        return true;
    }
    
    

    public static boolean checkRectangleToRectangle(Vec2 r1Position, float r1HalfWidth,  float r1HalfHeight, Vec2 r2Position, float r2HalfWidth,  float r2HalfHeight)
    {
        System.out.printf("checar");
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
    public CollisionsCheck()
    {
        

    }

   
}
