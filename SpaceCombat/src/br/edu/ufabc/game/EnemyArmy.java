package br.edu.ufabc.game;

import java.util.Vector;

import android.graphics.Canvas;

public class EnemyArmy {
	public Vector<Combo> army;

	
	public EnemyArmy(){
		army = new Vector<Combo>();
		
	}
	
	public void createEnemy(){
		Combo enemy = new Combo();
		army.add(enemy);
	}
	
	public void update(){

		for(Combo item : army){			
			item.update();
		}
			
	}	
	
	public void draw(Canvas canvas){
		for(int i = 0; i <  army.size(); i++){
			if(army.get(i).enemy.getX() < 0){
				army.remove(i);
			}
			else{
			army.get(i).draw(canvas);
			}
		}
	}
}

