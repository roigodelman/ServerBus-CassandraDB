package messaging;

import java.util.UUID;

public class Message<T> {
    public UUID packageId;
    public int packageNumber;
    public int totalPackges;
    public T data;

    public Message(UUID packageId,int packageNumber,int totalPackges,T data){
        this.data = data;
        this.packageId = packageId;
        this.packageNumber = packageNumber;
        this.totalPackges = totalPackges;
    }

}
