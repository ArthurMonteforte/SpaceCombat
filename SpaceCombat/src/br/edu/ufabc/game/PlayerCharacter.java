package br.edu.ufabc.game;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class PlayerCharacter extends GameObjects {

	private Bitmap figura;
	private Rect src;
	private Rect dst;
	
	private int spriteWidth;
	private int spriteHeight;
	private int currentSprite;
	
	private static final String TAG="PlayerCharacter";
	private int direcao;
	public static final int SOBE=0;
	public static final int DESCE=1;
	
	private static final int PASSO_DESCE=6;
	private static final int PASSO_SOBE=3;
	
	
	public PlayerCharacter() {
		try {
			InputStream is =  GameParameterSingleton.assetManager.open("robo_idle.png");
			figura = BitmapFactory.decodeStream(is);
			direcao= DESCE;
			spriteWidth = figura.getWidth() / 4;
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
		
		if (direcao == DESCE){
			setY(getY()+ PASSO_DESCE);
		}else{
			setY(getY()-PASSO_SOBE);
		}
		
		
		src.top=0;
		src.bottom = spriteHeight;
		src.left = currentSprite * spriteWidth; //
		src.right = src.left  + spriteWidth;
		
		dst.top = getY();
		dst.bottom = getY()+ getHeight();
		dst.left = getX();
		dst.right = getX()+getWidth();
		
		
		currentSprite = (currentSprite+1)%4;
		
	}

	
	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}

	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}

}
