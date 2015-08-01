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

	public static boolean detectColision(GameObjects g1, GameObjects g2){
		int g1Left, g1Right, g1Top, g1Bottom;
		int g2Left, g2Right, g2Top, g2Bottom;
		
		g1Left = g1.getBoundingBox().getX();
		g1Right =  g1.getBoundingBox().getX() + g2.getBoundingBox().getWidth();
		g1Top = g1.getBoundingBox().getY();
		g1Bottom = g1.getBoundingBox().getY() + g2.getBoundingBox().getHeight();
		
		g2Left = g2.getBoundingBox().getX();
		g2Right =  g2.getBoundingBox().getX() + g2.getBoundingBox().getWidth();
		g2Top = g2.getBoundingBox().getY();
		g2Bottom = g2.getBoundingBox().getY() + g2.getBoundingBox().getHeight();
		
		return(g1Left <= g2Right && g1Right >= g2Left && g1Top <= g2Bottom && g1Bottom >= g2Top);
	}
	
}
