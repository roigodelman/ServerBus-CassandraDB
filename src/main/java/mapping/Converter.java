package mapping;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import entities.WebContentSlice;

import java.nio.ByteBuffer;
import java.util.*;

//TODO need to be extension method
public class Converter {
    public static WebContentSlice toContentSlice(Row row){
        String url = row.getString("url");
        int slice = row.getInt("slice");
        UUID id = row.getUUID("id");
        ByteBuffer content = row.getBytes("content");

        //byte[] contentByte = content.getBytes();
        WebContentSlice temp = new WebContentSlice(id,url,slice,content.array());
        return  temp;
    }

    public static List<WebContentSlice> toArrayWebContent(ResultSet results ){
        LinkedList<WebContentSlice> returnValue = new LinkedList<WebContentSlice>();
        for (Row row : results) {
            WebContentSlice temp = toContentSlice(row);
            returnValue.add(temp);
        }
        return  returnValue;
    }
}
