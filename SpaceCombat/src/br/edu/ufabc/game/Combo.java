package br.edu.ufabc.game;

import android.graphics.Canvas;

public class Combo {
	public Enemy enemy;
	private boolean valid;
	int x,y;
	
	public Combo(){
		valid = true;
		
		enemy = new Enemy();
		//enemy.setX(GameParameterSingleton.SCREEN_WIDTH);
		//y=1 + (int)(Math.random() * GameParameterSingleton.SCREEN_HEIGHT);
		//enemy.setY(y);
		//enemy.updateDistortion();
		
		
	}
	
	public void update(){
		enemy.update();
	}
	
	public void draw(Canvas canvas){
		enemy.draw(canvas);
		
	}
	
}
