package br.edu.ufabc.game;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class ActivityIniciar extends Activity{

	private Handler handler;
	
	private GameScreen gameScreen;
	public MediaPlayer mp;
	
	
	EditText txtApelido;
	String apelido;
	
	int score;	
	String scoreText;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_iniciar);
		ajustaConteudo();
		
		handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				if(msg.what == 100){
					mp.stop();
					setContentView(R.layout.game_over);
				}
			}
		};
		
		mp = MediaPlayer.create(this, R.raw.terran_theme);
		
		
	}
	
	public void botaoOk(View view){
		score = GameParameterSingleton.PONTOS;
		scoreText = "" + score;
		
		Bundle param = new Bundle();
		param.putString("msg",apelido);
		param.putString("msg2",scoreText);
		Intent intent = new Intent(this, ActivityGameOver.class);
		intent.putExtras(param);
		startActivity(intent);	
	}
	
	public void ajustaConteudo(){
		// Inicia a tela Iniciar
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
	}
	
	
	public void botaoComecar(View view){
		// Ao clicar no botão Começar, inicia-se a view GameScreen (custom view do jogo)
		txtApelido = (EditText)findViewById(R.id.txtApelido);
		apelido = txtApelido.getText().toString();
		
		this.setupParamters();//configura os parametros do jogo
		
		gameScreen = new GameScreen(this);
		gameScreen.setHandler(handler);
		
		setContentView(gameScreen);
		Thread t = new Thread(gameScreen); 
		t.start();
		mp.start();	
		
	}
	
	//configura o singleton
	public void setupParamters(){
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		GameParameterSingleton.ORIENTATION = GameParameterSingleton.PORTRAIT;
		GameParameterSingleton.SCREEN_HEIGHT = size.y;
		GameParameterSingleton.SCREEN_WIDTH = size.x;
		GameParameterSingleton.assetManager = getAssets();
		
		//tira titulo (deixa jogo fullscree)
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //retrato
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}
	
	// desabilitando o botão back
	@Override
	public void onBackPressed() {
	}
	


	
}
