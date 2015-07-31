package br.edu.ufabc.game;

import android.graphics.Canvas;


//Classe que define todo objeto dentro do jogo, seja ele a nave ou inimigos, ou mesmo o tiro.
public abstract class GameObjects {

	private int x, y, width, height;
	public abstract void draw(Canvas canvas);
	public abstract void update();
	
	public void updateDistortion(){
		width =(int) (width * GameParameterSingleton.DISTORTION);
		height = (int) (height * GameParameterSingleton.DISTORTION);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	abstract void move();
	
	
}
