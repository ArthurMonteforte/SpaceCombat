package br.edu.ufabc.game;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
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
	private EnemyArmy enemyArmy;
	private BatAlienArmy baArmy;
	private Projectiles projectiles;
	public int tempo = 0;
	private boolean continua = true;
	

	Handler handler;
	

	
	public GameScreen(Context context) {
		super(context);
		init();
	
	}
	
	public void setHandler(Handler handler){
		this.handler = handler;
	}

	public void update(){
		if (update){
			if(tempo%1000 == 0){
				int random = 0 + (int)(Math.random()*100);
				if(random <= 30){
					baArmy.createBatAlienEnemy();
				}
				else{
					enemyArmy.createEnemy();
				}
				
				projectiles.createProjectile(robot.getBoundingBox().getX() + robot.getBoundingBox().getWidth(),
						robot.getY());
			}
			
			for(int i = 0; i < projectiles.projectiles.size(); i++){
				for(int j = 0; j < enemyArmy.army.size(); j++){
					if(GameParameterSingleton.detectColision(projectiles.projectiles.get(i),
							enemyArmy.army.get(j))){
						projectiles.projectiles.get(i).setMarcado(1);
						enemyArmy.army.get(j).setMarcado(1);
						GameParameterSingleton.PONTOS += 10;
						
					}
				}
			}
			
			for(int i = 0; i < projectiles.projectiles.size(); i++){
				for(int j = 0; j < baArmy.BAarmy.size(); j++){
					if(GameParameterSingleton.detectColision(projectiles.projectiles.get(i),
							baArmy.BAarmy.get(j))){
						projectiles.projectiles.get(i).setMarcado(1);
						baArmy.BAarmy.get(j).setMarcado();
						GameParameterSingleton.PONTOS += 50;
					}
				}
			}
			
			for(int j = 0; j < baArmy.BAarmy.size(); j++){
				if(GameParameterSingleton.detectColision(baArmy.BAarmy.get(j),
						robot)){
					robot.setMarcado(1);
					baArmy.BAarmy.get(j).setMarcado();
				}
			}
			
			for(int j = 0; j < enemyArmy.army.size(); j++){
				if(GameParameterSingleton.detectColision(enemyArmy.army.get(j),
						robot)){
					robot.setMarcado(1);
					enemyArmy.army.get(j).setMarcado(1);
				}
			}
			
			if(robot.getMarcado() == 1){
				Message msg = new Message();
				msg.what = 100; // algo do tipo - pegou	
				handler.sendMessage(msg); // envio a msg a ser tratada
				continua = false;
			}


				

			}
			
			
			bg.update();
			robot.update();
			enemyArmy.update();
			projectiles.update();
			baArmy.update();
			
		}
	
	



	
	//metodo CallBack, o android chama sozinho
	public void onDraw (Canvas canvas){
		bg.draw(canvas);
		robot.draw(canvas);
		enemyArmy.draw(canvas);
		projectiles.draw(canvas);
		baArmy.draw(canvas);
		canvas.drawText("Pontos: " + GameParameterSingleton.PONTOS, GameParameterSingleton.SCREEN_WIDTH - 100, 50, paint);
		
	}
	
	public void init (){
		update = true;
		paint = new Paint ();
		paint.setColor(Color.WHITE);
		
		GameParameterSingleton.PONTOS = 0;
		
		//criar os objetos do jogo
		bg = new Background();
		robot = new PlayerCharacter();
		//enemy = new Enemy();
		enemyArmy = new EnemyArmy();
		baArmy = new BatAlienArmy();
		
		//define fator de distorcao, pois cada tela de celular e diferente, ajusta o tamanho do app
		GameParameterSingleton.DISTORTION = (float) GameParameterSingleton.SCREEN_HEIGHT / bg.getAltura();
		bg.updateDistortion();
		
		robot.setX(10);
		robot.setY(100);
		robot.updateDistortion();
		
		projectiles = new Projectiles();
		
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
		while (continua){
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
