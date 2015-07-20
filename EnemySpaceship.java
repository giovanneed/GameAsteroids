
import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;
import edu.digipen.ObjectManager;
import edu.digipen.GameLevel;


public class EnemySpaceship extends GameObject
{

    public float shootElapsed = 0.f;
    public float shootSpawn = 3.f;
    
    private static final int size = 64;

    private ArenaLevel arenaLevel;

    public EnemySpaceship(ArenaLevel arena)
    {

       super("EnemySpaceship", size, size, "spaceship_enemy_red.png");

       arenaLevel = arena;
       
       setCircleCollider(size/2);
    }
    
    @Override
    public void update(float dt)
    {
       if(isInViewport() == false) kill();
       
       shootElapsed += dt;

        while(shootElapsed >= shootSpawn)
        {
            shoot();
            shootElapsed -= shootSpawn;
        }
    }
    
    private void shoot()
    {
 
       float x = PFRandom.randomRange(0, 3);
       float y = PFRandom.randomRange(0, 3);
       if (x == 2) x = 0.5f;
       if (y == 2) y = 0.5f;

       if (x == 0 && y == 0) x = 1.f;
       Vec2 direction = new Vec2(x, y);  //right med corner

       System.out.printf("VETOR ENEMY x %f  y %f \n",x,y);
       Bullet B = new Bullet("EnemyBullet","bullet_red.png");
       B.setPosition(getPosition());
       B.fire(direction);
       ObjectManager.addGameObject(B);
    }
    
    @Override
    public void collisionReaction(GameObject other)
    {
        if(other.getName().equals("bullet") ||other.getName().equals("doublebullet") ||other.getName().equals("Asteroid") ||other.getName().equals("EnemySpaceship"))
        {
            kill();
        }
    }
    

}
