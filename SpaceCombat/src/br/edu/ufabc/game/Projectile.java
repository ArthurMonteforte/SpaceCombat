package br.edu.ufabc.game;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	
	private Paint paint;
	
	int marcado = 0;
	
	public int getMarcado() {
		return marcado;
	}

	public void setMarcado(int marcado) {
		this.marcado = marcado;
	}

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
			
			paint = new Paint();
			paint.setColor(Color.WHITE);
			
			this.setX(x);
			this.setY(y);
			this.updateDistortion();
			
			this.getBoundingBox().setWidth(this.getWidth());
			this.getBoundingBox().setHeight(this.getHeight());
			getBoundingBox().setX(this.getX());
			getBoundingBox().setY(this.getY());
		
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
		canvas.drawRect(getBoundingBox().getX(),
				getBoundingBox().getY(),
				getBoundingBox().getX() + getBoundingBox().getWidth(),
				getBoundingBox().getY() + getBoundingBox().getHeight(),
				paint);
		canvas.drawBitmap(figura, src, dst, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int passoDistorcido = (int) (STEP * GameParameterSingleton.DISTORTION);
		setX(getX()+passoDistorcido);
		getBoundingBox().setX(this.getX());
		
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
