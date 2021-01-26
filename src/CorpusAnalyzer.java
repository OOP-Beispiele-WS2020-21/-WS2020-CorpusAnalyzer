import debug.LogMode;
import debug.Logger;

public class CorpusAnalyzer {

    public void loadTexts() {
        Logger.debug("Loading texts ...");
    }


    public static void main(String[] args) {
        Logger.setLogMode(LogMode.DEBUG);
        CorpusAnalyzer corpusAnalyzer = new CorpusAnalyzer();
        corpusAnalyzer.loadTexts();
    }
}
