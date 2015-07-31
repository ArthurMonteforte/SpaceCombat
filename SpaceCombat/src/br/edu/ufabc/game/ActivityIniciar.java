package br.edu.ufabc.game;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewAnimator;

public class ActivityIniciar extends Activity{

	private GameScreen gameScreen;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_iniciar);
		ajustaConteudo();
	}
	
	public void ajustaConteudo(){
		// Inicia a tela Iniciar
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
	}
	
	public void botaoComecar(View view){
		// Ao clicar no botão Começar, inicia-se a view GameScreen (custom view do jogo)
		
		this.setupParamters();//configura os parametros do jogo
		
		gameScreen = new GameScreen(this);
		
		setContentView(gameScreen);
		Thread t = new Thread(gameScreen); 
		t.start();
		
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
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}
	
}
