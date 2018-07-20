package pipline;

import com.datastax.driver.core.utils.UUIDs;
import entities.WebContentSlice;
import messaging.Message;
import messaging.ServiceBug;
import utils.ContentUrils;

import java.util.List;
import java.util.UUID;

public class DownloadDataToQueue {

    private ContentUrils contentUrils;
    private ServiceBug<Message> queue;
    private List<byte[]> dataList;


    public DownloadDataToQueue(ServiceBug<Message> queue){
        this.queue = queue;
    }

    public void processData(String url){
        StartDownloadData(url);
        queueTheData(url);
    }


    private void StartDownloadData(String url){
        contentUrils = new ContentUrils();
        try {
            dataList = contentUrils.downloadConent(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void queueTheData(String url){
        UUID id = UUIDs.timeBased();
        for (int i = 0; i<dataList.size(); i++) {
            WebContentSlice temp = new WebContentSlice(id,url,i,dataList.get(i));
            queue.push(new Message(id, i,dataList.size() -1, temp));
        }
    }

}
