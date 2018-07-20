package repository;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import entities.WebContentSlice;
import mapping.Converter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WebContentRepository {
    private final Cluster cluster;
    private final Session session;
    private static final String KEYSPACE_NAME = "website";

    public WebContentRepository() {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect(KEYSPACE_NAME);
    }

    public void createTable(String tableName) {
        String createTable = String.format(
                "CREATE TABLE IF NOT EXISTS %s.%s" +
                        "(id UUID, url text , slice int, content blob," +
                        "PRIMARY KEY(url,slice,id))"
                , KEYSPACE_NAME, tableName);
        session.execute(createTable);
    }

    public void insertData(String table, WebContentSlice data) {
        PreparedStatement prepared = session.prepare(
                "INSERT INTO website.ynet (url, slice,id,content) VALUES (?, ?, ?,?)");

        BoundStatement bound = prepared.bind();
        bound.setString("url", data.getUrl());
        bound.setInt("slice", data.getSlice());
        bound.setUUID("id", data.getId());
        bound.setBytes("content", ByteBuffer.wrap(data.getContent()));

        session.execute(bound);
    }

    public List<WebContentSlice> getAllData(String tableName) {
        ResultSet results = session.execute(String.format("SELECT * FROM %s.%s", KEYSPACE_NAME, tableName));
        ArrayList<WebContentSlice> returnValue = new ArrayList<WebContentSlice>();
        return Converter.toArrayWebContent(results);
    }

    public List<WebContentSlice> getById(String tableName, String url, UUID id) {

        Statement exampleQuery = QueryBuilder.select()
                .all()
                .from(KEYSPACE_NAME, tableName)
                .where((QueryBuilder.eq("id", id)))
                .and(QueryBuilder.eq("url", url))
                .orderBy(QueryBuilder.asc("slice"))
                .allowFiltering();

        ResultSet results = session.execute(exampleQuery);
        return Converter.toArrayWebContent(results);

    }
}