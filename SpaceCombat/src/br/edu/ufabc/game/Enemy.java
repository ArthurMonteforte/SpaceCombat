package br.edu.ufabc.game;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy extends GameObjects{

	private Bitmap figura;
	private Rect src;
	private Rect dst;
	
	private int spriteWidth;
	private int spriteHeight;
	private int currentSprite;
	
	private static final String TAG="Enemy";
	private final int STEP=3;
	
	public Enemy() {
		try {
			InputStream is =  GameParameterSingleton.assetManager.open("enemy.png");
			figura = BitmapFactory.decodeStream(is);
			
			spriteWidth = figura.getWidth() / 2;
			spriteHeight =  figura.getHeight();
			
			setWidth(spriteWidth);
			setHeight(spriteHeight);
			currentSprite =0;
			
			src =new Rect(0,0, getWidth(), getHeight());
			dst = new Rect();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(figura, src, dst, null);
	}

	@Override
	public void update() {
		int passoDistorcido = (int) (STEP * GameParameterSingleton.DISTORTION);
		setX(getX()-passoDistorcido);
		
		src.top=0;
		src.bottom = spriteHeight;
		src.left = currentSprite * spriteWidth; //
		src.right = src.left  + spriteWidth;
		
		dst.top = getY();
		dst.bottom = getY()+ getHeight();
		dst.left = getX();
		//dst.right = getX()+getWidth();
		dst.right = dst.left+getWidth();
		
		
		currentSprite = (currentSprite+1)%2;
		
	}

	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}

}
