package br.edu.ufabc.game;

import java.util.Vector;

import android.graphics.Canvas;

public class Projectiles {
    
    public Vector<Projectile> projectiles;

    
    public Projectiles(){
        projectiles = new Vector<Projectile>();
        
    }
    
    public void createProjectile(int x, int y){
        Projectile projectile = new Projectile(x, y);
        projectiles.add(projectile);
    }
    
    public void update(){

        for(Projectile item : projectiles){            
            item.update();
        }
            
    }    
    
    public void draw(Canvas canvas){
        for(int i = 0; i <  projectiles.size(); i++){
            projectiles.get(i).draw(canvas);
        }
    }
}