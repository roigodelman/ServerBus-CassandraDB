package pipline;

import messaging.Message;
import messaging.ServiceBug;

import java.util.ArrayList;

public class Monitoring {
    private ServiceBug<Message> queue;

    public Monitoring(ServiceBug<Message> queue){
        this.queue = queue;
    }

    public void start(){
        boolean run = true;
        Boolean[] arrayList = null;
        while(run){
           Message message = queue.pop();
            if(arrayList == null){
                arrayList = new Boolean[message.totalPackges];
            }
            arrayList[message.packageNumber] = true;

            if(message.packageNumber == message.totalPackges){
                break;
            }
        }

        for (boolean index: arrayList) {
            if(index == false){
                System.out.println("Not all the packages are deliver");
                return;
            }
        }

        System.out.println("All the message are deliver");

    }

}
