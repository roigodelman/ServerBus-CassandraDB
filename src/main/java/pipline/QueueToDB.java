package pipline;

import entities.WebContentSlice;
import messaging.Message;
import messaging.ServiceBug;
import repository.WebContentRepository;

public class QueueToDB {

    private ServiceBug<Message> processQueue;
    private ServiceBug<Message> doneQueue;
    public Boolean runQueue;
    private WebContentRepository repository;

    public QueueToDB(ServiceBug<Message> processQueue,ServiceBug<Message> doneQueue){
        this.processQueue = processQueue;
        this.doneQueue = doneQueue;
        runQueue = true;
        repository = new WebContentRepository();

    }

    public void StartRun(String tableName){
        repository.createTable(tableName);
        checkQueue(tableName);
    }

    private void checkQueue(String tableName){
        while(runQueue){
            if(processQueue.Size() > 0){
               Message<WebContentSlice> newMessage = processQueue.pop();
                repository.insertData(tableName,newMessage.data);
                doneQueue.push(newMessage);
            }
        }
    }

}
