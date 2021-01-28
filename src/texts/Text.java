package texts;

public class Text {

    public final String title;
    public final String raw;

    public Text(String title, String raw) {
        this.title = title;
        this.raw = raw;
    }

    public String[] tokenize(String delimiter) {
        return this.raw.split(delimiter);
    }

    public String[] tokenize() {
        return this.tokenize(" ");
    }

}
