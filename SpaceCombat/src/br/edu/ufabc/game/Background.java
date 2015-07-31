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
	private int altura, largura;
	String pictureName;
	Rect src; //retangulo original do bg
	Rect first, sec; //retangulos que compoe o bg infinito
	
	private static final String TAG ="InfiniteBG";
	
	//passo em pixels (velocidade com a qual o BG se move)
	private final int STEP=10;
	
	public Background(){
		try {
			InputStream is = GameParameterSingleton.assetManager.open("bg.png");
			figura = BitmapFactory.decodeStream(is);
			altura = figura.getHeight();
			largura = figura.getWidth();
			
			src = new Rect(0,0, largura, altura);
			first = new Rect();
			sec = new Rect();
			
		} catch (Exception e) {
			Log.d(TAG, "Erro ao montar img");
		}
	}
	
	//define como o bg se move
	public void update(){
		
		int passoDistorcido = (int) (STEP * GameParameterSingleton.DISTORTION);
		
		first.left -= passoDistorcido;
		first.right -= passoDistorcido;
		//first.top  = 0 ;
		//first.bottom = altura;
		
		sec.left -= passoDistorcido;
		sec.right -= passoDistorcido;
		//sec.top  = 0;
		//sec.bottom = altura;
		
		if (first.right <= 0){
			first.right = sec.right+largura;
			first.left = sec.right;
			
		}
		
		if (sec.right <= 0){
			sec.left = first.right;
			sec.right = first.right + largura;
		}
		
	}
	
	//precisa ter um canvas pois desenha nele
	public void draw(Canvas canvas){
		//System.out.println("DESENHA");
		canvas.drawBitmap(figura, src, first, null);
		canvas.drawBitmap(figura, src, sec, null);
		
	}
	
	public void updateDistortion(){
		setLargura((int) (this.getLargura() * GameParameterSingleton.DISTORTION));
		setAltura( (int) (this.getAltura() * GameParameterSingleton.DISTORTION));
		
		first.left = 0;
		first.top  = 0 ;
		first.right = largura;
		first.bottom = altura;
		
		sec.top  = 0;
		sec.left = largura;
		sec.right = sec.left + largura;
		sec.bottom = altura;
		
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}
	
	
}
