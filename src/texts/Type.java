package texts;

public class Type implements Comparable<Type> {

    public final String value;
    private int inCorpusFrequency;

    public Type(String value) {
        this.value = value;
        inCorpusFrequency = 0;
    }

    public void increaseFrequency() {
        inCorpusFrequency++;
    }

    public int getFrequency() {
        return inCorpusFrequency;
    }

    @Override
    public int compareTo(Type o) {
        return this.inCorpusFrequency - o.getFrequency();
    }
}
