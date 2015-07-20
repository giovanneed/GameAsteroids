import edu.digipen.GameObject;

import edu.digipen.math.Vec2;

public class Bullet extends GameObject
{
    private static final int size = 16;
    
    private float speed = 450.f;
    
    public Bullet(String name, String textureName)
    {
        //"CircleSmall.png"
        super(name, size, size,textureName);
        setCircleCollider(size / 2);
    }
    
    public void fire(Vec2 direction)
    {
        Vec2 vel = Vec2.scale(direction, speed);
        setVelocity(vel);
    }
    
    @Override
    public void update(float dt)
    {
        if(isInViewport() == false)
        {
            kill();
        }
    }
    
    @Override
    public void collisionReaction(GameObject other)
    {
        if(other.getName().equals("Asteroid") || other.getName().equals("bullet")  || other.getName().equals("EnemyBullet"))
        {
            kill();
        }
    }
}
