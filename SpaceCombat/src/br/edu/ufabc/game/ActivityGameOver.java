package br.edu.ufabc.game;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityGameOver extends Activity implements OnClickListener{
	TextView pontuacao;
	TextView txtNome;
	Button btnOk;
	//EditText txtApelido;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		ajustaConteudo();
		pontuacao = (TextView)findViewById(R.id.pontuacao);
		//txtNome = (TextView)findViewById(R.id.txtNome);
		btnOk = (Button)findViewById(R.id.btnOk);
		btnOk.setOnClickListener(this);
	}
	
	public void ajustaConteudo(){
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
				
		if (param != null){
			String msg = param.getString("msg");
			TextView txtNome = (TextView)findViewById(R.id.txtNome);
			txtNome.setText(msg);
			Toast.makeText(this,"Dados recebidos com sucesso",Toast.LENGTH_SHORT).show();
			
		}
	}
	
	@Override
	public void onClick(View v) {
		InsertData task1 = new InsertData();
		task1.execute(new String[]{"http://gamespacecombat.byethost7.com/insertnew.php"});
		
	}
	
	private class InsertData extends AsyncTask<String, Void, Boolean>{
		ProgressDialog dialog = new ProgressDialog(ActivityGameOver.this);
		

		@Override
		protected void onPreExecute(){
			dialog.setMessage("Enviando os dados");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... urls) {
			for (String url : urls){
				try {
					ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
					pairs.add(new BasicNameValuePair("txtName",txtNome.getText().toString()));
					pairs.add(new BasicNameValuePair("txtScore",pontuacao.getText().toString()));
					
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					Toast.makeText(ActivityGameOver.this, e.toString(), Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					Toast.makeText(ActivityGameOver.this, e.toString(), Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}
		
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(result == true){
				Toast.makeText(ActivityGameOver.this, "Sucesso", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(ActivityGameOver.this, "Erro", Toast.LENGTH_SHORT).show();
			}
			dialog.dismiss();
		}
		
	}
}
