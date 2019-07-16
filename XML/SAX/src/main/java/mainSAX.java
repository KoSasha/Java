import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

public class mainSAX {

    private static ArrayList<Msg> msgs = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("src/main/resources/file.xml"), handler);

        for (Msg msg : msgs) {
            System.out.println("Кому: " + msg.getTo() + "; От кого: " + msg.getFrom() + "; Тема: " + msg.getTitle() + "; Сообщение: " + msg.getBody());
        }

    }

    private static class XMLHandler extends DefaultHandler {
        private String to;

        private String from;

        private String title;

        private String body;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("msg")) {
                to = attributes.getValue("to");
                from = attributes.getValue("from");
                title = attributes.getValue("title");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String inf = new String(ch, start, length);
            if (!inf.isEmpty()) {
                body = inf;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((to != null && !to.isEmpty()) && (from != null && !from.isEmpty()) &&
                    (title != null && !title.isEmpty()) && (body != null && !body.isEmpty())) {
                msgs.add(new Msg(to, from, title, body));
                to = null;
                from = null;
                title = null;
                body = null;
            }
        }
    }
}
