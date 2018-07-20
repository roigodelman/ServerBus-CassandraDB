package messaging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceBug<T> {

    private BlockingQueue<T> queue;

    public ServiceBug(){
        queue = new LinkedBlockingQueue<T>();
    }

    public void push(T item){
        try {
            queue.put(item);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public T pop(){
        return queue.poll();
    }

    public int Size(){
        return queue.size();
    }

}
