package at.mschwaer.chucknorrisBild;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by mschwaer on 17.02.2016.
 */
public class GetImageWeb {
    private String webpath;
    private String url;
    private String BitmapUrl = "";
    private Bitmap image = null;

    public GetImageWeb() {
        webpath = "http://www.enlighten.at/htl/chucknorris/";
        url = webpath + "index.html";
    }
    public void processFeed(final int nr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://www.enlighten.at/htl/chucknorris/index.html").get();
                    Elements imgs = doc.getElementsByTag("img");
                    if ((nr+1) < imgs.size())
                        BitmapUrl = webpath + imgs.get(nr+1).attr("src");
                    Log.d("processFeed", "BitmpaUrl:" + BitmapUrl + " nr:" + (nr+1) + " imgs.size:" + imgs.size());
                } catch (Exception e) {
                    Log.d("processFeed", "Error in processFeed" + e.toString());
                    //Wahlweise Zuordnung des Bildnamens
                    BitmapUrl = (nr+1)+".jpg";
                }
            }
        }).start();
    }
    public Bitmap getBitmap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("getBitmap", BitmapUrl);
                    HttpURLConnection connection = (HttpURLConnection)new URL(BitmapUrl).openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    image = BitmapFactory.decodeStream(input);
                    input.close();
                } catch (Exception e) {
                    Log.d("processFeed", "Error in processFeed" + e.toString());
                }
            }
        }).start();
        return image;
    }

    public String getWebpath () {
        return BitmapUrl;
    }


}
