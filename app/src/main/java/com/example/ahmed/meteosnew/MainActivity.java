package com.example.ahmed.meteosnew;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView   titre_item;
    TextView   max_temp_item;
    TextView   min_temp_item;
    TextView   lcation_item;
    ImageView  image_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titre_item  = (TextView)findViewById(R.id.titre_tv);
        max_temp_item = (TextView)findViewById(R.id.temp_max_tv);
        min_temp_item = (TextView)findViewById(R.id.temp_min_tv);
        lcation_item = (TextView)findViewById(R.id.ville_tv);
        image_item = (ImageView)findViewById(R.id.icon_iv) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        new TestRequest().execute();

    }

    private class TestRequest extends AsyncTask<Void, Void, ClimatElement> {

        @Override
        protected ClimatElement doInBackground(Void... params) {

            //on a fait un erequest avec la clee api
            String urlTest = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=d3ccdd38ccdcf83759bafbed1ec72d46";
            Log.i("TestRequest", "je ne suis pas suncro");

            //on travail avec la laibrairie okhttp
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlTest)
                    .build();
            ClimatElement climaElement = null;

            try {
                Response response = client.newCall(request).execute();
                String bodyReponse = response.body().string();
                //on a cree un climaelement pour convertir avec json
                climaElement = parseJSON(bodyReponse);
                parseJSON(bodyReponse);
                Log.i("Reponse", response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return climaElement;
        }

        @Override
        //la method equ'on a generer pour aficher
        protected void onPostExecute(ClimatElement climatElement) {
            super.onPostExecute(climatElement);

            String titreItem = climatElement.getNomDuJour() +" " + climatElement.getJour() + " " + climatElement.getNomDuMois() +" " +
                    climatElement.getAnnee();


            titre_item.setText(titreItem);
            max_temp_item.setText(" Température Max " + climatElement.getMaxTemp());
            min_temp_item.setText(" Température Min " + climatElement.getMinTemp());
            lcation_item.setText(climatElement.getLcation());

            climatElement.getVille();


        }
    }

    private ClimatElement parseJSON(String bodyReponse) throws JSONException {
        JSONObject mainJson = new JSONObject(bodyReponse);
        String Ville = mainJson.get("name").toString();
        JSONObject sys = mainJson.getJSONObject("sys");
        String Pays = sys.get("country").toString();
        String lcation = Ville + "," + Pays;

        JSONObject main = mainJson.getJSONObject("main");
        String minTemp = main.get("temp_min").toString();
        String maxTemp = main.get("temp_max").toString();

        //on converti la date
        String dt = mainJson.get("dt").toString();
        long timeStamp = Integer.valueOf(dt);
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis(timeStamp * 1000);
        //pour fair edes test ds le debeug
        //String resultat = mydate.get(Calendar.DAY_OF_MONTH) + "." + mydate.get(Calendar.MONTH) + "." + mydate.get(Calendar.YEAR);

        //formatter mois
        String nomDuMois = Utilites.getMois(mydate.get(Calendar.MONTH));
        String nomDuJour = Utilites.getJour(mydate.get(Calendar.DAY_OF_WEEK));


       ClimatElement climatElement = new ClimatElement(Ville,Pays,lcation,minTemp,maxTemp,timeStamp,nomDuMois,nomDuJour,mydate.get(Calendar.DAY_OF_MONTH),
               mydate.get(Calendar.MONTH),
               mydate.get(Calendar.YEAR));
        return climatElement;

    }


}
