import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;

import java.io.*;
import java.util.ArrayList;

public class mainDOM {
    private static ArrayList<Msg> msgs = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/file.xml"));
        // список всех элементов msgs внутри корневого элемента
        NodeList msgElements = document.getDocumentElement().getElementsByTagName("msg");
        for (int i = 0; i < msgElements.getLength(); i++) {
            Node msg = msgElements.item(i);
            NamedNodeMap attributes = msg.getAttributes();
            msgs.add(new Msg(attributes.getNamedItem("to").getNodeValue(), attributes.getNamedItem("from").getNodeValue(),
                    attributes.getNamedItem("title").getNodeValue(), attributes.getNamedItem("body").getNodeValue()));
        }
        for (Msg msg : msgs) {
            System.out.println("Кому: " + msg.getTo() + "; От кого: " + msg.getFrom() + "; Тема: " + msg.getTitle() + "; Сообщение: " + msg.getBody());
        }
    }
}
