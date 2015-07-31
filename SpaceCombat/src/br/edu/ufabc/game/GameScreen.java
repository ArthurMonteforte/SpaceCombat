package br.edu.ufabc.game;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//classe que desenha a dela do jogo...� a view propriamente dita.
//Deve ser uma thread pois deve se auto desenhar.
public class GameScreen extends View implements Runnable{

	private boolean update; //variavel que controla o update
	private int i;
	private Paint paint; //necessario para desenhar.
	private static final String TAG = "GameScreen";
	private Background bg;
	private PlayerCharacter robot;
	private Enemy enemy;
	private Combo combo; //desenha um combo de inimigos
	
	public GameScreen(Context context) {
		super(context);
		init();
		//i=0;
	
	}

	public void uptade(){
		if (update){
			bg.update();
			robot.update();
			combo.update();
			//enemy.update();
		}
	
	}
		
	//metodo CallBack, o android chama sozinho
	public void onDraw (Canvas canvas){
		bg.draw(canvas);
		robot.draw(canvas);
		combo.draw(canvas);
		//enemy.draw(canvas);
	}
	
	public void init (){
		update = true;
		paint = new Paint ();
		paint.setColor(Color.BLACK);
		
		//criar os objetos do jogo
		bg = new Background();
		robot = new PlayerCharacter();
		//enemy = new Enemy();
		combo = new Combo();
		
		//define fator de distorcao, pois cada tela de celular e diferente, ajusta o tamanho do app
		GameParameterSingleton.DISTORTION = (float) GameParameterSingleton.SCREEN_HEIGHT / bg.getAltura();
		bg.updateDistortion();
		
		robot.setX(10);
		robot.setY(100);
		robot.updateDistortion();
		
		
	}

	public boolean onTouchEvent(MotionEvent evt){
		if(evt.getAction()== MotionEvent.ACTION_DOWN){
			robot.setDirecao(PlayerCharacter.SOBE);
			return true;
		}else if(evt.getAction()== MotionEvent.ACTION_UP){
			robot.setDirecao(PlayerCharacter.DESCE);
			return true;
		}
		return false;
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
