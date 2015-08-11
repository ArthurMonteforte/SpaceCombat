package br.edu.ufabc.game;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	
	private Paint paint;
	
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
			
			paint = new Paint();
			paint.setColor(Color.WHITE);
			
			this.setX(GameParameterSingleton.SCREEN_WIDTH);
			int y=1 + (int)(Math.random() * GameParameterSingleton.SCREEN_HEIGHT);
			this.setY(y);
			this.updateDistortion();
			
			this.getBoundingBox().setWidth(this.getWidth());
			this.getBoundingBox().setHeight(this.getHeight());
			getBoundingBox().setX(this.getX());
			getBoundingBox().setY(this.getY());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(getBoundingBox().getX(),
		getBoundingBox().getY(),
		getBoundingBox().getX() + getBoundingBox().getWidth(),
		getBoundingBox().getY() + getBoundingBox().getHeight(),
		paint);
		canvas.drawBitmap(figura, src, dst, null);
	}

	@Override
	public void update() {
		int passoDistorcido = (int) (STEP * GameParameterSingleton.DISTORTION);
		setX(getX()-passoDistorcido);
		getBoundingBox().setX(getX()-passoDistorcido);
		
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
