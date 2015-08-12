package br.edu.ufabc.game;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	RankingBD rankingBD;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ajustaConteudo();
	}
	
	public void ajustaConteudo(){
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
	}
	
	public void botaoIniciar(View view){
		String msg = "1";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, ActivityIniciar.class);

		intent.putExtras(param);

		startActivity(intent);
	}

	public void botaoRanking(View view){
		rankingBD = new RankingBD(this);
		setContentView(R.layout.ranking);
		// preciso de uma instancia "legível" da minha base
		SQLiteDatabase db = rankingBD.getReadableDatabase();
		
		Cursor cursor;
		
		cursor = db.query("tblRanking", new String[]{"name","score"}, null , null, null, null, "score desc");

		String resultado="";
		// há registros no resultado da query?
		if (cursor.moveToFirst()){
			
			// itero sobre estes registros
			do{
				resultado += cursor.getInt(1)+" - "+cursor.getString(0)+"\n";
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		TextView txtTodasTarefas = (TextView)findViewById(R.id.txtRanking);
		txtTodasTarefas.setText(resultado);
		
	}


	public void botaoInstrucao(View view){
		String msg = "1";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, Instructions.class);

		intent.putExtras(param);

		startActivity(intent);
	}

	
	public void voltarInicio(View view){
		setContentView(R.layout.activity_main);
	}
	
	public void botaoSair(View view){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

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
