package br.edu.ufabc.game;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//classe que desenha a dela do jogo...é a view propriamente dita.
//Deve ser uma thread pois deve se auto desenhar.
public class GameScreen extends View implements Runnable {

	private boolean update; //variavel que controla o update
	private Paint paint; //necessario para desenhar.
	private static final String TAG = "GameScreen";
	private Background bg;
	private PlayerCharacter robot;
	private Combo combo; //desenha um combo de inimigos
	private EnemyArmy enemyArmy;
	private BatAlien bat;
	public int tempo = 0;
	
	public GameScreen(Context context) {
		super(context);
		init();
		//i=0;
	
	}

	public void update(){
		if (update){
			if(tempo%1000 == 0){
				//int i = 1 + (int)(Math.random() * ((20 - 1) + 1));
				enemyArmy.createEnemy();
			}
			bg.update();
			robot.update();
			combo.update();
			enemyArmy.update();
			bat.update();
			//enemy.update();
		}
	
	}
		
	//metodo CallBack, o android chama sozinho
	public void onDraw (Canvas canvas){
		bg.draw(canvas);
		robot.draw(canvas);
		combo.draw(canvas);
		enemyArmy.draw(canvas);
		bat.draw(canvas);
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
		enemyArmy = new EnemyArmy();
		bat = new BatAlien();
		
		//define fator de distorcao, pois cada tela de celular e diferente, ajusta o tamanho do app
		GameParameterSingleton.DISTORTION = (float) GameParameterSingleton.SCREEN_HEIGHT / bg.getAltura();
		bg.updateDistortion();
		
		robot.setX(10);
		robot.setY(100);
		robot.updateDistortion();
		
		// ajustando boundingBox do robô
		robot.getBoundingBox().setWidth(robot.getWidth());
		robot.getBoundingBox().setHeight(robot.getHeight());
		robot.getBoundingBox().setX(robot.getX());
		robot.getBoundingBox().setY(robot.getY());
		
		
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
	
	
	//essa classe é uma thread pois deve se auto desenhar. Controla o loop do jogo
	@Override
	public void run() {
		while (true){
			try {
				update();
				postInvalidate();//invalida a view atual e forcar a invocacao do DRAW
				tempo += 50;
				Thread.sleep(50); //regula os frames
			} catch (Exception e) {
				Log.d(TAG, "Erro no loop");
			}
			
		}
	}
	
}
