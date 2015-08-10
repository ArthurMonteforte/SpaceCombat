package br.edu.ufabc.game;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Projectile extends GameObjects{
	
	private Bitmap figura;
	private Rect src;
	private Rect dst;
	
	private int spriteWidth;
	private int spriteHeight;
	private int currentSprite;
	
	private static final String TAG="Projectile";
	private final int STEP=3;
	
	public Projectile(int x, int y){
		try {
			InputStream is =  GameParameterSingleton.assetManager.open("Projectile.png");
			figura = BitmapFactory.decodeStream(is);
		
			spriteWidth = figura.getWidth() / 4;
			spriteHeight =  figura.getHeight();
		
			setWidth(spriteWidth);
			setHeight(spriteHeight);
			currentSprite = 0;
		
			src =new Rect(0,0, getWidth(), getHeight());
			dst = new Rect();
		
		}
		catch (Exception e) {
		// TODO: handle exception
		}
		
		this.setX(x);
		this.setY(y);
		this.updateDistortion();
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(figura, src, dst, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int passoDistorcido = (int) (STEP * GameParameterSingleton.DISTORTION);
		setX(getX()+passoDistorcido);
		
		src.top=0;
		src.bottom = spriteHeight;
		src.left = currentSprite * spriteWidth; //
		src.right = src.left  + spriteWidth;
		
		dst.top = getY();
		dst.bottom = getY()+ getHeight();
		dst.left = getX();
		//dst.right = getX()+getWidth();
		dst.right = dst.left+getWidth();
		
		
		currentSprite = (currentSprite+1)%3;
		
	}

	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}

}
