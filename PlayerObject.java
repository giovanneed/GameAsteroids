import edu.digipen.GameObject;
import edu.digipen.InputManager;
import edu.digipen.ObjectManager;
import edu.digipen.GameLevel;
import edu.digipen.math.Vec2;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerObject extends GameObject
{
    private ArenaLevel arenaLevel;
    
    private static final int size = 96;
    private static final String textureName = "alienship.png";//"Ship.png";
    
    public Vec2 direction = new Vec2(1.f, 0.f);

    public float speed = 150.f;
    public float rotateSpeed = 120.f;
    
    public PlayerObject(ArenaLevel arena)
    {
        super("PlayerShip", size, size, textureName);
        arenaLevel = arena;
        setCircleCollider(size / 2);
    }
    
    @Override
    public void update(float dt)
    {
        /*if(InputManager.isPressed(KeyEvent.VK_D))
        {
            setPositionX(getPositionX() + 1);
        }
        if(InputManager.isPressed(KeyEvent.VK_A))
        {
            setPositionX(getPositionX() - 1);
        }
        if(InputManager.isPressed(KeyEvent.VK_W))
        {
            setPositionY(getPositionY() + 1);
        }
        if(InputManager.isPressed(KeyEvent.VK_S))
        {
            setPositionY(getPositionY() - 1);
        }*/

        if(InputManager.isPressed(KeyEvent.VK_W))
        {
            //Vec2 dP = Vec2.scale(direction, speed * dt);
            //getPosition().add(dP);
            
            addForce(Vec2.scale(direction, speed),0.016f);
        }else
        {
            //clearForces();
            //Vec2 test = new Vec2(1.f, 0.f);
            //test.scale(direction, speed);
            //test.negate();
            //addForce(test,0.016f);
            //System.out.printf("desaceleraaaa speed %f \n ", speed);
         

        }

        
        
        if(InputManager.isPressed(KeyEvent.VK_S))
        {
            //Vec2 dP = Vec2.scale(direction, -speed * dt);
            //getPosition().add(dP);
            addForce(Vec2.scale(direction, -speed),0.016f);
        }
        
        if(InputManager.isPressed(KeyEvent.VK_A))
        {
            setRotation(getRotation() + rotateSpeed * dt);
            
            float radians = (float)Math.toRadians(getRotation());
            
            direction.setX((float)Math.cos(radians));
            direction.setY((float)Math.sin(radians));
        }
        
        if(InputManager.isPressed(KeyEvent.VK_D))
        {
            setRotation(getRotation() - rotateSpeed * dt);
            
            float radians = (float)Math.toRadians(getRotation());
            
            direction.setX((float)Math.cos(radians));
            direction.setY((float)Math.sin(radians));
        }
        
        //System.out.printf("BULLET DIRECTION x %f  y %f  ROTATION %f \n",direction.getX(),direction.getY(),getRotation());
        if(InputManager.isTriggered(KeyEvent.VK_SPACE))
        {
            Bullet B = new Bullet("bullet","bullet.png");
            
            B.setPosition(getPosition());
            
            B.fire(direction);

            ObjectManager.addGameObject(B);
        }
        
        
        if(InputManager.isTriggered(KeyEvent.VK_P))
        {
            Bullet B1 = new Bullet("bullet","bullet.png");
            Bullet B2 = new Bullet("bullet","bullet.png");
            float rotate = getRotation();
            int auxX = 0;
            int auxY = 0;
            
            if((rotate >= 0 && rotate <=45) || (rotate >= 135 && rotate <=270) || (rotate >= 315))
            {
                auxY = 20;
                auxX = 0;
            } else {
                
                auxY = 0;
                auxX = 20;
            }
            
        
            B1.setPosition(getPositionX()-auxX,getPositionY()-auxY);
            B2.setPosition(getPositionX()+auxX,getPositionY()+auxY);
            B1.fire(direction);
            B2.fire(direction);
            ObjectManager.addGameObject(B1);
            ObjectManager.addGameObject(B2);
        }
        
        GameObjectUtils.Wrapping(this,size/2,size/2);
        
       ArrayList<GameObject> listAsteroid = ObjectManager.getGameObjectsByName("Asteroid");
       for (GameObject obj : listAsteroid) {
           
           Asteroid asteroid = (Asteroid)obj;
           
           if(CollisionsCheck.checkCircleToCircle(getPosition(), size/2, obj.getPosition(), asteroid.size/2))
           {
            
            }
        }
        
       if(Collision.CheckCollisionByList(this,ObjectManager.getGameObjectsByName("Asteroid")))
        {
           arenaLevel.restart();
           kill();
        }
       
    }
    
    @Override
    public void collisionReaction(GameObject other)
    {
        if(other.getName().equals("EnemySpaceship") || other.getName().equals("EnemyBullet") )
        {
            arenaLevel.restart();
            kill();
            
        }
    }
    
   
        
}