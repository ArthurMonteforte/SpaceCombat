package br.edu.ufabc.game;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

//classe que desenha a dela do jogo...� a view propriamente dita.
//Deve ser uma thread pois deve se auto desenhar.
public class GameScreen extends View implements Runnable{

	private boolean update; //variavel que controla o update
	private int i;
	private Paint paint; //necessario para desenhar.
	private static final String TAG = "GameScreen";
	private Background bg;
	
	public GameScreen(Context context) {
		super(context);
		init();
		i=0;
		
		
	
	}

	public void uptade(){
		if (update){
			//i++;
			bg.update();
		}
	
	}
		
	//metodo CallBack, o android chama sozinho
	public void onDraw (Canvas canvas){
		//canvas.drawText("Valor do i = " + i, 50, 100, paint);
		bg.draw(canvas);
	}
	
	public void init (){
		//i=0;
		update = true;
		paint = new Paint ();
		paint.setColor(Color.BLACK);
		
		//criar os objetos do jogo
		bg = new Background();
		
		//definir fator de distorcao, posi cada tela de celular e diferente
		GameParameterSingleton.DISTORTION = (float) GameParameterSingleton.SCREEN_HEIGHT / bg.getFiguraAltura();
		bg.setFiguraLargura((int)(bg.getFiguraLargura()* GameParameterSingleton.DISTORTION));
		bg.setFiguraAltura((int)(bg.getFiguraAltura()* GameParameterSingleton.DISTORTION));
		
	}

	//essa classe � uma thread pois deve se auto desenhar. Controla o loop do jogo
	@Override
	public void run() {
		while (true){
			try {
				uptade();
				postInvalidate();//invalida a view atual e forcar a invocacao do DRAW
				Thread.sleep(50); //regula os frames
			} catch (Exception e) {
				Log.d(TAG, "Erro no loop");
			}
			
		}
	}
	
}
