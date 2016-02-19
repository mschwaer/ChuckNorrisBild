package at.mschwaer.chucknorrisBild;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ChuckNorrisActivity extends AppCompatActivity implements View.OnClickListener {

    ChuckNoris theChuck;
    Random randomGenerator;
    int nr=0;
    GetImageWeb mypic;
    AsyncTask<String, String, Bitmap> li;
    ProgressDialog pDialog;
    Boolean mInternet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuck);

        theChuck = new ChuckNoris();
        randomGenerator = new Random();

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        mypic = new GetImageWeb();
        Log.d("OnCreate", "gestartet");

        if (!isOnline()) {
            Toast.makeText(getBaseContext(), "Keine Internetverbindung!", Toast.LENGTH_SHORT).show();
        }
    }


    public void showSpruch(View view) {
        TextView spruchview = (TextView) findViewById(R.id.TextView);
        int randomInt = randomGenerator.nextInt(theChuck.getChuckSpruchAnz());
        spruchview.setText(theChuck.getChuckSpruch(randomInt).toCharArray(), 0, theChuck.getChuckSpruchSize(randomInt));

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button:
                TextView spruchview = (TextView) findViewById(R.id.TextView);
                int randomInt = randomGenerator.nextInt(theChuck.getChuckSpruchAnz());
                spruchview.setText(theChuck.getChuckSpruch(randomInt).toCharArray(), 0, theChuck.getChuckSpruchSize(randomInt));
                mypic.processFeed(randomInt);
                Log.d("OnClick", "webpath" + mypic.getWebpath());
                final ImageView imageView = (ImageView) findViewById(R.id.imageView);
                int counter =0;
                //if (mypic.getBitmap() != null) imageView.setImageBitmap(mypic.getBitmap());

                //
               // pDialog = new ProgressDialog(v.getContext());
                //pDialog.setMessage("Loading Image ....");
                //pDialog.show();
                li = new LoadImage(v.getContext(),isOnline()).execute(mypic.getWebpath());
                //li = new LoadImage().execute(mypic.getWebpath());
                try {
                    imageView.setImageBitmap(li.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
               // pDialog.dismiss();

                //
                Log.d("OnClick", "Nr" + randomInt);

                if (isOnline() && !mInternet) {
                    Toast.makeText(getBaseContext(), "Internetverbindung vorhanden!", Toast.LENGTH_SHORT).show();
                    mInternet = true;
                }
                if (!isOnline() && mInternet) {
                    Toast.makeText(getBaseContext(), "Internetverbindung unterbrochen!", Toast.LENGTH_SHORT).show();
                    mInternet = false;
                }


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

    public boolean isOnline() {
        ConnectivityManager cm =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
