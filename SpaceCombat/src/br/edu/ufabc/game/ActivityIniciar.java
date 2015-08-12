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
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_iniciar);
		ajustaConteudo();
		
		handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				if(msg.what == 100){
					setContentView(R.layout.game_over);
				}
			}
		};
		
		mp = MediaPlayer.create(this, R.raw.terran_theme);
		
		
	}
	
	public void ajustaConteudo(){
		// Inicia a tela Iniciar
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
		/*handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				
				if(msg.what==100){
				
					setContentView(R.layout.game_over);
				}

			}
		};*/
	}
	
	/*public void botaoTeste(View view){
		EditText txtApelido = (EditText)findViewById(R.id.txtApelido);
		String msg = txtApelido.getText().toString();
		Bundle param2 = new Bundle();
		param2.putString("msg",msg);
		
		Intent intent = new Intent(this, Ranking.class);
		intent.putExtras(param2);
		startActivity(intent);
	}*/
	
	public void botaoComecar(View view){
		// Ao clicar no botão Começar, inicia-se a view GameScreen (custom view do jogo)
		
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
	
	
	/*public void mostraViewFinish(){
		setContentView(R.layout.game_over);
	}
	*/
	// desabilitando o botão back
	@Override
	public void onBackPressed() {
	}
	


	
}
