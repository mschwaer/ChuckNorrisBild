package at.mschwaer.chucknorrisBild;

/**
 * Created by mschwaer on 18.02.2016.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class FileOperations {
    private Context context;
    public FileOperations(Context context) {
        this.context = context;
    }

    public Boolean fileExists(String fname){
        File file = new File(context.getFilesDir(), fname);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }

    }
    public Bitmap readPic(String fname){
        Bitmap response = null;
        FileInputStream fis = null;

        try {
            String myFile = context.getFilesDir() + "/"+ fname;
            fis = new FileInputStream(new File(myFile));
            response = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return response;

    }

    public Boolean writePic(String fname, Bitmap fcontent){
        FileOutputStream outputStream;
        Bitmap.CompressFormat bcf = Bitmap.CompressFormat.PNG;
        try {
            String myFile = context.getFilesDir() + "/"+ fname;
            Log.d("writePic",myFile);
            File file = new File(context.getFilesDir(), fname);

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //Check Format
            String format = fname.substring(Math.max(0,fname.length() - 4));
            switch (format) {
                case ".png":
                    bcf = Bitmap.CompressFormat.PNG;
                    break;
                case ".jpg":
                    bcf = Bitmap.CompressFormat.JPEG;
                    break;
                case "jpeg":
                    bcf = Bitmap.CompressFormat.JPEG;
                    break;
            }

            outputStream = context.openFileOutput(fname,context.MODE_PRIVATE);
            fcontent.compress(bcf,100,outputStream);
            outputStream.flush();
            outputStream.close();

            Log.d("Suceess","Sucess");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean write(String fname, String fcontent){
        try {

            String fpath = "/sdcard/"+fname+".txt";

            File file = new File(fpath);

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fcontent);
            bw.close();

            Log.d("Suceess","Sucess");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String read(String fname){

        BufferedReader br = null;
        String response = null;

        try {

            StringBuffer output = new StringBuffer();
            String fpath = "/sdcard/"+fname+".txt";

            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"n");
            }
            response = output.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return response;

    }

}