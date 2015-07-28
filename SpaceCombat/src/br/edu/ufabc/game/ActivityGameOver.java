package br.edu.ufabc.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityGameOver extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		ajustaConteudo();
	}
	
	public void ajustaConteudo(){
		// Inicia a tela Iniciar

	}
	
	public void botaoOk(View view){
		// Ao clicar no botão Começar, inicia-se a Custom View com o Jogo

	}
}
