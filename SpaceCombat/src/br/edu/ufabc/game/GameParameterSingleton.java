package br.edu.ufabc.game;

import android.content.res.AssetManager;

//Classe que mantem todos os parametros globais do jogo
public class GameParameterSingleton {
	
	public static final int PORTRAIT =0;
	public static final int LANDSCAPE =1;
	
	public static int ORIENTATION;
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static float DISTORTION = 1.0f;
	
	//quem gerencia os assets (figuras) do jogo
	public static AssetManager assetManager;

	/*private static final GameParameterSingleton config  = new GameParameterSingleton();
	
	private GameParameterSingleton(){
		
	}
	
	static GameParameterSingleton getInstance(){
		return config;
	}*/
	
}
