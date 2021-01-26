package texts;

public class Type implements Comparable<Type> {

    public final String value;
    private int inCorpusCount;

    public Type(String value) {
        this.value = value;
        this.inCorpusCount = 0;
    }

    public void increaseCount() {
        this.inCorpusCount++;
    }

    public int getCount() {
        return inCorpusCount;
    }

    @Override
    public int compareTo(Type type) {
        return this.inCorpusCount - type.getCount();
    }
}
