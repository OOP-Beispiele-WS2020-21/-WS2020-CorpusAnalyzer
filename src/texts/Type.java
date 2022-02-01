package texts;

/**
 * Diese Klasse repräsentiert eine einzelne Type innerhalb eines Korpus. Die Frequenz (Häufigkeit)
 * wird dabei zu Beginn mit 0 initialisiert und kann über öffentliche Methoden schrittweise
 * erhöht und auch ausgelesen werden. Zwei Typen können hinsichtlich der aktuellen Frequenz verglichen
 * werden, wobei Typen mit höherer Auftretenshäufigkeit hinter solchen mit geringerer einsortiert werden.
 */
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
