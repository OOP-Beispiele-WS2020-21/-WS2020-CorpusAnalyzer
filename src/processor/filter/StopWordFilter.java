package processor.filter;

/**
 * Textfilter für die Verarbeitung einzelner Texte: Entfernt alle im übergebenen Text auftretende
 * Stopwörter. Die Liste der Stopwörter wird hier über ein String-Array direkt in der Klasse definiert.
 */
public class StopWordFilter implements TextFilter {

    // Stop words taken from: https://gist.github.com/sebleier/554280
    private static final String[] STOP_WORDS = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};

    @Override
    public String filter(String text) {
        String filteredText = text;
        for(String stopWord: STOP_WORDS) {
            filteredText = filteredText.replaceAll(" " + stopWord + " ", " ");
        }
        return filteredText;
    }

    @Override
    public String getFilterName() {
        return "StopWordFilter";
    }

}
