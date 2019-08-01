import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fis);

        MongoClient mongoClient = MongoClients.create(properties.getProperty("db.host"));
        MongoDatabase db = mongoClient.getDatabase(properties.getProperty("db.name"));
        MongoCollection<Document> table = db.getCollection(properties.getProperty("db.table"));

        Document doc = new Document("name","Tot").append("phone","700").append("lang",Arrays.asList("python","c"));
        table.insertOne(doc);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set",new BasicDBObject().append("clients",90));
        BasicDBObject searchQuery = new BasicDBObject().append("name","Tot");
        table.updateOne(searchQuery, newDocument);

        for (Document document : table.find()) {
            System.out.println(document.toJson());
        }

        table.deleteOne(searchQuery);
    }
}
