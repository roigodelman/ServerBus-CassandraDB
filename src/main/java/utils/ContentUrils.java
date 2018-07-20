package utils;



import messaging.Message;
import messaging.ServiceBug;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ContentUrils {

    private  static  final  int KB = 1024;
    private  static  final  int NUM_OF_KB = 10;
    private static final int BUFFER_SIZE = KB * NUM_OF_KB;


    public static List<byte[]> downloadConent(String url)
            throws Exception {
        URL newUrl = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) newUrl.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpConn.getInputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            LinkedList<byte[]> result = new LinkedList<byte[]>();
            while ((inputStream.read(buffer)) != -1) {
                result.add(buffer.clone());
            }

            inputStream.close();
            return result;
        } else {
            System.out.println("Something wring with url");
        }
        return null;
    }
}
