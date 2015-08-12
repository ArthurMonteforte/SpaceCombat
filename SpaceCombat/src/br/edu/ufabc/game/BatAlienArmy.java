package br.edu.ufabc.game;

import java.util.Vector;

import android.graphics.Canvas;

public class BatAlienArmy {
	public Vector<BatAlien> BAarmy;

	
	public BatAlienArmy(){
		BAarmy = new Vector<BatAlien>();
		
	}
	
	public void createBatAlienEnemy(){
		BatAlien enemy = new BatAlien();
		BAarmy.add(enemy);
	}
	
	public void update(){

		for(BatAlien item : BAarmy){			
			item.update();
		}
			
	}	
	
	public void draw(Canvas canvas){
		for(int i = 0; i <  BAarmy.size(); i++){
			if((BAarmy.get(i).getX() < 0 - BAarmy.get(i).getSpriteWidth())
					|| BAarmy.get(i).getMarcado() >= 5){
				BAarmy.remove(i);
			}
			else{
				BAarmy.get(i).draw(canvas);
			}
		}
	}
}

