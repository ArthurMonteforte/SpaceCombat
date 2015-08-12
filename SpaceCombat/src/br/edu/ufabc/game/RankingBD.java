package br.edu.ufabc.game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RankingBD extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "dbRanking";
	private static final int    DB_VERS =1;
	
	public RankingBD(Context ctx){
	    super(ctx,DB_NAME,null,DB_VERS);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String SQL = "CREATE TABLE tblRanking ("
				+ "      name VARCHAR(20),"
				+ "      score INT);";
		
		db.execSQL(SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	

}
