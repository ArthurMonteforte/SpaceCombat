package br.edu.ufabc.game;

import java.util.Vector;

import android.graphics.Canvas;

public class EnemyArmy {
	public Vector<Enemy> army;

	
	public EnemyArmy(){
		army = new Vector<Enemy>();
		
	}
	
	public void createEnemy(){
		Enemy enemy = new Enemy();
		army.add(enemy);
	}
	
	public void update(){

		for(Enemy item : army){			
			item.update();
		}
			
	}	
	
	public void draw(Canvas canvas){
		for(int i = 0; i <  army.size(); i++){
			if((army.get(i).getX() < 0 - army.get(i).getSpriteWidth())
					|| army.get(i).getMarcado() == 1){
				army.remove(i);
			}
			else{
			army.get(i).draw(canvas);
			}
		}
	}
}

