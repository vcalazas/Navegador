package com.example.vcalazas.navegador;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed_url;/* Caixa de texto para as urls */
    WebView wv_web;/* Janela do navegador */
    Toolbar toolbar;/* Menu, o qual acopla os botões e caixa de texto */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_url     = (EditText) findViewById(R.id.url);
        wv_web     = (WebView) findViewById(R.id.web);
        toolbar    = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        wv_web.getSettings().setJavaScriptEnabled(true);
        wv_web.loadUrl("http://www.google.com/");
        wv_web.setWebViewClient(new WebViewClient(){
            /* Codigo responsvel pelo loader de carregamento */
            /* - */@Override
            /* - */public void onPageStarted(WebView web, String url, Bitmap favicon){
            /* - */    wv_web.setVisibility(web.INVISIBLE);/* Oculta o webView */
            /* - */    ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
            /* - */    pb.setVisibility(web.VISIBLE);/* mostra o loader */
            /* - */}

            /* - */@Override
            /* - */public void onPageFinished(WebView web, String url){
            /* - */    ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
            /* - */    pb.setVisibility(web.INVISIBLE);/* oculta o loader */
            /* - */    wv_web.setVisibility(web.VISIBLE);/* mostra o webView */
            /* - */}
            /*--------------------------------------------------------------------------------------*/
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.btnSend:
                if(ed_url.getText().toString().isEmpty()/*verifica se o editText esta vazio*/){
                    Toast.makeText(this, "Insira uma url", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "url: "+"http://www."+ed_url.getText().toString(), Toast.LENGTH_SHORT).show();
                    wv_web.loadUrl("http://www."+ed_url.getText().toString());/* Faz o webView ir para pagina selecionada */
                }
                return true;

            case R.id.btnVoltar:
                if(wv_web.canGoBack()/*verifica se o webView pode voltar*/){
                    wv_web.goBack();/* Vouta para pagina anterior */
                }else{
                    Toast.makeText(this, "Sem páginas anteriores", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.btnAvancar:
                if(wv_web.canGoForward()/*verifica se o webView pode prosseguir*/){
                    wv_web.canGoForward();/* passa para a pagina seguinte */
                }else{
                    Toast.makeText(this, "Sem páginas posteriores", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
