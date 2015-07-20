import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;
import edu.digipen.ObjectManager;
import edu.digipen.GameLevel;

public class Asteroid extends GameObject
{
    public final int size;

    private ArenaLevel arenaLevel;
    
    public Asteroid(int size_in, ArenaLevel arena, String textureName)
    {

       super("Asteroid", size_in, size_in,textureName);
       size = size_in;
       arenaLevel = arena;
       
       setCircleCollider(size/2);
    }
     @Override
    public void update(float dt)
    {
       GameObjectUtils.Wrapping(this,size/2,size/2);
    }
    
    @Override
    public void collisionReaction(GameObject other)
    {
       
        
        
        if(other.getName().equals("bullet") ||other.getName().equals("doublebullet"))
        {
             Vec2 Position = other.getPosition();
             //float x  = obj.getPositionX();
             //float y = obj.getPositionY();
             arenaLevel.updateScore(1);

            kill();
            
            switch(size)
            {
                case 128:
                {
                    for(int i=0; i< 4; i++)
                    {
                        Asteroid A = new Asteroid(32,arenaLevel,"aestroid_grey.png");
                        float angle = PFRandom.randomRange(0, 360);
                        Vec2 direction = new Vec2((float)Math.cos((double)angle),(float)Math.sin((double)angle));
                        A.setVelocity(Vec2.scale(direction, 10.f));
                        A.setPosition(Position);
                        ObjectManager.addGameObject(A);
                    }
                    break;
                }
                 case 32:
                {
                    for(int i=0; i< 8; i++)
                    {
                        Asteroid A = new Asteroid(16,arenaLevel,"aestroid_grey.png");
                        float angle = PFRandom.randomRange(0, 360);
                        Vec2 direction = new Vec2((float)Math.cos((double)angle),(float)Math.sin((double)angle));
                        A.setVelocity(Vec2.scale(direction, 10.f));
                        A.setPosition(Position);
                        ObjectManager.addGameObject(A);
                    }
                    break;
                }
               /* case 16:
                {
                    for(int i=0; i< 8; i++)
                    {
                        Asteroid A = new Asteroid(16);
                        float angle = PFRandom.randomRange(0, 360);
                        Vec2 direction = new Vec2((float)Math.cos((double)angle),(float)Math.sin((double)angle));
                        A.setVelocity(Vec2.scale(direction, 10.f));
                        A.setPosition(Position);
                        ObjectManager.addGameObject(A);
                    }
                    break;
                }*/
            }
        }
    }
}