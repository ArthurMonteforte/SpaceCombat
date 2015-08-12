package br.edu.ufabc.game;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	//private GameScreen gameScreen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*gameScreen = new GameScreen(this);
		
		setContentView(gameScreen);
		
		Thread t = new Thread(gameScreen); */
	}
	
	public void botaoIniciar(View view){
		//setContentView(R.layout.tela_iniciar);
		
		String msg = "1";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, ActivityIniciar.class);

		intent.putExtras(param);

		startActivity(intent);
	}

	public void botaoRanking(View view){
		//setContentView(R.layout.tela_iniciar);
		
		String msg = "2";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, Ranking.class);

		intent.putExtras(param);

		startActivity(intent);
	}


	public void botaoInstrucao(View view){
		//setContentView(R.layout.tela_iniciar);
		
		String msg = "1";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, Instructions.class);

		intent.putExtras(param);

		startActivity(intent);
	}

	
	public void botaoSair(View view){
		Intent intent1 = new Intent(Intent.ACTION_MAIN); 
		finish();
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	
	public void onStart(){
		super.onStart();
	}
	
    protected void onStop(){
    	super.onStop();
    }
	
	public void onPause(){
		super.onPause();
	}
	
	public void onResume(){
		super.onResume();
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
	
	public void onRestart(){
		super.onRestart();
	}
}
