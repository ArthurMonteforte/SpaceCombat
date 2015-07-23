package br.edu.ufabc.game;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class ActivityIniciar extends Activity{

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
		// Ao clicar no botão Começar, inicia-se a Custom View com o Jogo

	}
}
