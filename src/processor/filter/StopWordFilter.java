package processor.filter;

import texts.Text;
import utils.FileLoader;

import java.io.File;
import java.io.FileNotFoundException;

public class StopWordFilter implements TextFilter {

    // See: https://en.wikipedia.org/wiki/Stop_word
    private static final String[] DEFAULT_STOP_WORDS = {"a", "able", "about", "across", "after", "all", "almost", "also", "am", "among", "an", "and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "dear", "did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him", "his", "how", "however", "i", "if", "in", "into", "is", "it", "its", "just", "least", "let", "like", "likely", "may", "me", "might", "most", "must", "my", "neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own", "rather", "said", "say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "would", "yet", "you", "your"};

    private String[] stopWords;

    public StopWordFilter(File stopWordFile) {
        try {
            stopWords = FileLoader.loadLinesFromText(stopWordFile);
        } catch (FileNotFoundException e) {
            stopWords = DEFAULT_STOP_WORDS;
            e.printStackTrace();
        }
    }

    @Override
    public Text filter(Text text) {
        String filteredText = text.raw;
        for (String stopWord : stopWords) {
            filteredText = filteredText.replaceAll(" " + stopWord + " ", "");
        }
        return new Text(text.title, filteredText);
    }
}
