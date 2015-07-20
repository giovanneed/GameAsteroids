import edu.digipen.GameLevel;
import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.InputManager;
import edu.digipen.Graphics;
import edu.digipen.GameLevelManager;
import edu.digipen.text.TextObject;
import edu.digipen.text.FontTypes;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;

import java.awt.event.KeyEvent;

public class ArenaLevel extends GameLevel
{
    public float newEnemyelapsed = 0.f;
    public float newEnemytimeToSpawn = 3.f;
    public float newEnemySpeed = 50.f;
    
    public float elapsed = 0.f;
    public float timeToSpawn = 3.f;
    public float asteroidSpeed = 10.f;
    TextObject text;
    public static int ScoreValue;
    
    @Override
    public void create()
    {

        ScoreValue = 0;
        text  =  new TextObject("ScoreText","Score:" + ScoreValue,FontTypes.ARIAL_16);
        int x = (int)(Graphics.getWindowWidth() / 2.f) + 50;
        int y = (int)(Graphics.getWindowHeight() / 2.f) + 50;
        text.setPosition(-350,200);
        //set front
        
        TextObject hint  =  new TextObject("Hint","P - DoubleShoot",FontTypes.ARIAL_16);
        hint.setPosition(250,200);
        

    }
    
    @Override
    public void initialize()
    {
        GameObject guy = new PlayerObject(this);
        ObjectManager.addGameObject(guy);
    }
    
    @Override
    public void update(float dt)
    {
        elapsed += dt;

        while(elapsed >= timeToSpawn)
        {
            SpawnAsteroid();
            elapsed -= timeToSpawn;
        }
        
        newEnemyelapsed += dt;

        while(newEnemyelapsed >= newEnemytimeToSpawn)
        {
            SpawnNewEnemy();
            newEnemyelapsed -= newEnemytimeToSpawn;
        }
        
        if(InputManager.isTriggered(KeyEvent.VK_R))
        {
            ScoreValue  = 0;
            updateScore(0);
            GameLevelManager.restartLevel();
        }
    }
    
    private Position randomPosition()
    {
        float x = -Graphics.getWindowWidth() / 2.f;
        float y = PFRandom.randomRange(-Graphics.getWindowHeight() / 2.f, Graphics.getWindowHeight() / 2.f);
        
        int randomNum = 1 + (int)(Math.random()*4);
        
        switch(randomNum)
        {
            case 1:
            {
    
                break;
            }
            case 2:
            {
                 y = Graphics.getWindowWidth() / 2.f;
                 x = PFRandom.randomRange(-Graphics.getWindowHeight() / 2.f, Graphics.getWindowHeight() / 2.f);
                
                break;
            }
            case 3:
            {
                x = Graphics.getWindowWidth() / 2.f;
                y = PFRandom.randomRange(-Graphics.getWindowHeight() / 2.f, Graphics.getWindowHeight() / 2.f);
        
                break;
            }
            case 4:
            {
                y = -Graphics.getWindowWidth() / 2.f;
                x = PFRandom.randomRange(-Graphics.getWindowHeight() / 2.f, Graphics.getWindowHeight() / 2.f);
        
                break;
            }
            
        }
      
        
       Position randomP = new Position(x,y);
       return randomP;
    }
   
    private void SpawnNewEnemy()
    {
        EnemySpaceship E = new EnemySpaceship(this);
        
        Position p = randomPosition();
        
        Vec2 Position = new Vec2(p.getX(), p.getY());
        
        E.setPosition(Position);
        
        Vec2 vel = new Vec2(Position);
        
        vel.normalize();
        vel.negate();
        vel = Vec2.scale(vel, newEnemySpeed);
        
        E.setVelocity(vel);
        
        ObjectManager.addGameObject(E);
    }
    
    private void SpawnAsteroid()
    {

        Asteroid A = new Asteroid(128,this,"aestroid_dark.png");
        
        Position p = randomPosition();
        
        Vec2 Position = new Vec2(p.getX(), p.getY());
        
        A.setPosition(Position);
        
        Vec2 vel = new Vec2(Position);
        
        vel.normalize();
        vel.negate();
        vel = Vec2.scale(vel, asteroidSpeed);
        
        A.setVelocity(vel);
        
        ObjectManager.addGameObject(A);
    }
    public void updateScore( int valor)
    {
        ScoreValue+=valor;
        text.setText("Score: " + ScoreValue);
    }
    public void restart()
    {
        ScoreValue  = 0;
        updateScore(0);
        GameLevelManager.restartLevel();
    }
            
    @Override
    public void uninitialize()
    {
        ObjectManager.removeAllObjectsByName("PlayerShip");
        ObjectManager.removeAllObjectsByName("Asteroid");
        ObjectManager.removeAllObjectsByName("bullet");
        ObjectManager.removeAllObjectsByName("EnemySpaceship");
        ObjectManager.removeAllObjectsByName("enemyBullet");
    }
}
