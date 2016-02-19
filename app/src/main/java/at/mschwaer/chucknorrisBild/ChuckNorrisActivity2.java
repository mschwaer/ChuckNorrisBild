package at.mschwaer.chucknorrisBild;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ChuckNorrisActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ChuckNoris theChuck;
    private Random randomGenerator;
    private int nr=0;
    ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuck);

        theChuck = new ChuckNoris();
        randomGenerator = new Random();

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        img = (ImageView) findViewById(R.id.imageView);
        Log.d("OnCreate", "gestartet");
    }


    public void showSpruch(View view) {
        TextView spruchview = (TextView) findViewById(R.id.TextView);
        int randomInt = randomGenerator.nextInt(theChuck.getChuckSpruchAnz());
        spruchview.setText(theChuck.getChuckSpruch(randomInt).toCharArray(),0,theChuck.getChuckSpruchSize(randomInt));

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button:
                TextView spruchview = (TextView) findViewById(R.id.TextView);
                int randomInt = randomGenerator.nextInt(theChuck.getChuckSpruchAnz());
                spruchview.setText(theChuck.getChuckSpruch(randomInt).toCharArray(), 0, theChuck.getChuckSpruchSize(randomInt));
                Log.d("OnClick", "webpath" + randomInt);

                int counter =0;
                //if (mypic.getBitmap() != null) imageView.setImageBitmap(mypic.getBitmap());

                new LoadImage().execute("http://www.enlighten.at/htl/chucknorris/" + randomInt + ".jpg");
                Toast.makeText(getApplicationContext(), "Picture loaded", Toast.LENGTH_SHORT).show();
                Log.d("OnClick", "Nr" + randomInt);

                break;
            case R.id.button2:
                Button b2 = (Button)findViewById(R.id.button2);
                String[] strArr = b2.getText().toString().split(" ");
                if (strArr.length == 0) {
                    nr = Integer.getInteger(strArr[1]);
                }
                nr=nr+1;
                b2.setText("Klicked " + nr);

                break;
        }
    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        Bitmap bitmap;
        ProgressDialog pDialog;
        Context theA;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ChuckNorrisActivity2.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Log.d("getImage", "Kein Bild gefunden!");

            }
        }
    }
}
