package br.edu.ufabc.game;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

//classe que vai definir o plano de fundo do jogo
//essa classe é diferente do gameObject pelo simples fato de 
//nao ser colidivel, embora apresente caracteristicas semelhantes.
public class Background {
	
	private Bitmap figura;
	private int figuraAltura, figuraLargura;
	String pictureName;
	Rect src; //retangulo original do bg
	Rect first, sec; //retangulos que compoe o bg infinito
	
	private static final String TAG ="InfiniteBG";
	
	public Background(){
		try {
			InputStream is = GameParameterSingleton.assetManager.open("bg.png");
			figura = BitmapFactory.decodeStream(is);
			figuraAltura = figura.getHeight();
			figuraLargura = figura.getWidth();
			
			src = new Rect(0,0, figuraLargura, figuraAltura);
			first = new Rect();
			sec = new Rect();
			
		} catch (Exception e) {
			Log.d(TAG, "Erro ao montar img");
		}
	}
	
	//define como o bg se move
	public void update(){
		first.left = 0;
		first.left = figuraLargura;
		first.top  = 0 ;
		first.bottom = figuraAltura;
		
	}
	
	//precisa ter um canvas pois desenha nele
	public void draw(Canvas canvas){
		//System.out.println("DESENHA");
		canvas.drawBitmap(figura, src, first, null);
		
	}
	
	public void updateDistortion(){
		
	}

	public int getFiguraAltura() {
		return figuraAltura;
	}

	public void setFiguraAltura(int figuraAltura) {
		this.figuraAltura = figuraAltura;
	}

	public int getFiguraLargura() {
		return figuraLargura;
	}

	public void setFiguraLargura(int figuraLargura) {
		this.figuraLargura = figuraLargura;
	}
	
	
}
