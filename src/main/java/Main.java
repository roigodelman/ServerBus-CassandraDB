import messaging.Message;
import messaging.ServiceBug;
import pipline.DownloadDataToQueue;
import pipline.Monitoring;
import pipline.QueueToDB;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start Program");

        ServiceBug<Message> watingQueue = new ServiceBug<Message>();
        ServiceBug<Message> doneQueue = new ServiceBug<Message>();

        DownloadDataToQueue downloadDataToQueue = new DownloadDataToQueue(watingQueue);
        QueueToDB queueToDB = new QueueToDB(watingQueue,doneQueue);
        Monitoring monitoring = new Monitoring(doneQueue);

        downloadDataToQueue.processData("www.ynet.co.il");
        queueToDB.StartRun("ynet");
        monitoring.start();

        System.out.println("End Program");

    }

}
