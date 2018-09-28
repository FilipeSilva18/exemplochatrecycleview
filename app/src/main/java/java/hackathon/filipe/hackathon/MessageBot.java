package java.hackathon.filipe.hackathon;

public class MessageBot {

    private String message;

    private boolean isBot;

    private int type;

    private String tag;

    public MessageBot(String message, boolean isBot, int type, String tag) {
        this.message = message;
        this.isBot = isBot;
        this.type = type;
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public MessageBot setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isBot() {
        return isBot;
    }

    public MessageBot setBot(boolean bot) {
        isBot = bot;
        return this;
    }

    public int getType() {
        return type;
    }

    public MessageBot setType(int type) {
        this.type = type;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public MessageBot setTag(String tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public String toString() {
        return "MessageBot{" +
                "message='" + message + '\'' +
                ", isBot=" + isBot +
                ", type=" + type +
                ", tag='" + tag + '\'' +
                '}';
    }
}
