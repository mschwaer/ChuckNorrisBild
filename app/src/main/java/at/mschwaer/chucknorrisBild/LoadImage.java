package at.mschwaer.chucknorrisBild;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by mschwaer on 17.02.2016.
 */
public class LoadImage extends AsyncTask<String, String, Bitmap> {

    Bitmap bitmap;
    ProgressDialog pDialog;
    ImageView img;
    Context theA;
    Boolean internetAccess = false;


    public LoadImage(Context theC, Boolean status){
        super();
        theA = theC;
        internetAccess = status;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(theA);
        pDialog.setMessage("Loading Image ....");
        pDialog.show();

    }
    protected Bitmap doInBackground(String... args) {
        String filename ="";
        bitmap = null;
        FileOperations myFo;
        try {
            // Check if Bitmap ist vorhanden local
            Log.d("doInBackground"," args[0] " +args[0]);
            String[] urls = args[0].split("/");
            if (urls.length > 0)
                filename = urls[urls.length-1];
            else
                filename = args[0];
            Log.d("doInBackground"," filename " +filename);
            myFo = new FileOperations(theA);
            if (myFo.fileExists(filename)) {
                bitmap = myFo.readPic(filename);
                Log.d("doInBackground"," load lokal file " +filename);
            } else {
                if (internetAccess) {
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
                    Log.d("doInBackground", " save file  load internet" + filename);
                    myFo.writePic(filename, bitmap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap image) {

        if(image != null){
            //img.setImageBitmap(image);
            pDialog.dismiss();

        }else{

            pDialog.dismiss();
            Log.d("getImage", "Kein Bild gefunden!");

        }
    }
}