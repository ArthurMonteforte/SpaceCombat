package br.edu.ufabc.game;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Ranking extends Activity{
	RankingBD rankingBD;
	String resultado[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ranking);
		ajustaConteudo();
		rankingBD = new RankingBD(this);

	}

	public void ajustaConteudo(){
		// Inicia a tela Iniciar
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
	}
	
	public void todosScores(View view){
		setContentView(R.layout.ranking);
		
		SQLiteDatabase db = rankingBD.getReadableDatabase();
		
		Cursor cursor;
		int i = 0;
		cursor = db.query("tblRanking", new String[]{"name","score"}, null , null, null, null, "score desc");

		
		if (cursor.moveToFirst()){
			do{
				resultado[i] = cursor.getString(0)+ " - "+cursor.getInt(1);
				i++;
			} while (cursor.moveToNext());
		}
		
		
		ArrayAdapter<String> arrayAdapter;
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, resultado);
		
		db.close();
	}

	public void voltar(View view){
		setContentView(R.layout.activity_main);
		
	}
}
