package br.edu.ufabc.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Instructions extends Activity {
	ListView listInst;
	TextView txtInst;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_instrucoes);
		ajustaConteudo();
		txtInst = (TextView)findViewById(R.id.txtInst);
	}

	public void ajustaConteudo(){
		// Inicia a tela Iniciar
		Intent intent = getIntent();
		Bundle param = intent.getExtras();
	}
	
	public void back(View v){
		String msg = "1";

		Bundle param = new Bundle();
		param.putString("msg", msg);

		Intent intent = new Intent(this, MainActivity.class);

		intent.putExtras(param);

		startActivity(intent);

	}
	
	public void clickPort(View v){
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://www.json-generator.com/api/json/get/cgvmAHREPm?indent=0"});
		
	}
	
	public void clickIng(View v){
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://www.json-generator.com/api/json/get/cbgrGgZNlu?indent=2"});
		
	}
	
	
	private class ReadData extends AsyncTask<String, Void, Boolean>{

		ProgressDialog dialog = new ProgressDialog(Instructions.this);
		@Override
		protected void onPreExecute(){
			dialog.setMessage("Lendo os dados...");
			dialog.show();
		}
		
		String text = "";
		ArrayList<String> list1;
		String conteudo = "";
		
		@Override
		protected Boolean doInBackground(String... urls){
			InputStream is1;
			
			
			for(String url1 : urls){
				try{
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					
					HttpResponse response = client.execute(post);
					is1 = response.getEntity().getContent();
					
				}
				catch(ClientProtocolException e){
					Toast.makeText(Instructions.this, e.toString(), Toast.LENGTH_SHORT).show();
					return false;
				}
				catch(IOException e){
					Toast.makeText(Instructions.this, e.toString(), Toast.LENGTH_SHORT).show();
					return false;
				}
				
				BufferedReader reader;
				
				try {
					reader = new BufferedReader(new InputStreamReader(is1, "iso-8859-1"),8);
					String line = null;
					while((line = reader.readLine()) != null){
						text += line +"\n";
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				list1 =  new ArrayList<String>();
				
				try {
					JSONArray jArray = new JSONArray(text);
					for(int i=0;i<jArray.length();i++){
						JSONObject jsonData = jArray.getJSONObject(i);
						conteudo =   jsonData.getString("text1")+"\n\n"+jsonData.getString("text2");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result){
			if(result==true){
				txtInst.setText(conteudo);
			}
			else{
				Toast.makeText(Instructions.this, "Erro na leitura dos dados", Toast.LENGTH_SHORT).show();
			}
			dialog.dismiss();
		}
		
	}
}
