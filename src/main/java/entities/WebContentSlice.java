package entities;

import java.util.UUID;

public class WebContentSlice {
    private String url;
    private int slice;
    private byte[] content;
    // private Date date;
    private UUID id;

    public WebContentSlice(UUID id,String url, int slice, byte[] content) {
        this.id = id;
        this.url = url;
        this.slice = slice;
        this.content = content;
    }

    public int getSlice() {
        return slice;
    }

    public void setSlice(int slice) {
        this.slice = slice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public UUID getId(){
        return  id;
    }
}
