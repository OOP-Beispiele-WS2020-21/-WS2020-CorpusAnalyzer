package texts;

public class Type implements Comparable<Type> {

    public final String value;
    private int inCorpusFrequency;
    private float relativeInCorpusFrequency;

    public Type(String value) {
        this.value = value;
        this.inCorpusFrequency = 0;
        this.relativeInCorpusFrequency = 0;
    }

    public void increaseCount(int totalTokenCountForCorpus) {
        this.inCorpusFrequency++;
        this.relativeInCorpusFrequency = ((float) inCorpusFrequency / totalTokenCountForCorpus) * 100;
    }

    public int getFrequency() {
        return inCorpusFrequency;
    }

    public float getRelativeFrequency() {
        return relativeInCorpusFrequency;
    }

    @Override
    public int compareTo(Type type) {
        return this.inCorpusFrequency - type.getFrequency();
    }
}
