package br.edu.ufabc.game;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityGameOver extends Activity{
	String apelido;
	String score;
	RankingBD rankingBD;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_score);
		ajustaConteudo();
	}
	
	public void ajustaConteudo(){
		
		
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
		if (param != null){
			apelido = param.getString("msg");
			score = param.getString("msg2");
			TextView novoApelido = (TextView)findViewById(R.id.novoApelido);
			TextView novoScore = (TextView)findViewById(R.id.novoScore);
			novoApelido.setText(param.getString("msg"));
			novoScore.setText(param.getString("msg2"));
			
		}

	}
	
	
	public void voltar(View v) {
		rankingBD = new RankingBD(this);
		SQLiteDatabase db = rankingBD.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put("name", apelido);  
		cv.put("score", score);
		
		db.insert("tblRanking", null, cv);
		db.close();
		
		Toast.makeText(this,"Salvo com sucesso!",Toast.LENGTH_SHORT).show();
		
		String msg = "1";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, MainActivity.class);

		intent.putExtras(param);

		startActivity(intent);

	}
	
}
