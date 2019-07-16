public class Msg {

    private  String to;

    private String from;

    private String title;

    private String body;

    Msg(String to, String from, String title, String body) {
        setTo(to);
        setFrom(from);
        setTitle(title);
        setBody(body);
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
