import config.AppConfig;
import debug.LogMode;
import debug.Logger;
import processor.TextProcessor;
import processor.filter.LowerCaseFilter;
import processor.filter.PunctuationFilter;
import texts.FolgerCorpusTextLoader;
import texts.Text;

import java.io.File;
import java.util.ArrayList;

public class CorpusAnalyzer {

    public void loadTexts() {
        Logger.debug("Loading texts ...");
        File textFileDirectory = new File(AppConfig.TEXT_FILE_DIRECTORY_PATH);
        ArrayList<Text> texts = FolgerCorpusTextLoader.loadFromDirectory(textFileDirectory);
        TextProcessor processor = new TextProcessor();
        processor.addFilter(new LowerCaseFilter());
        processor.addFilter(new PunctuationFilter());
        for(Text text: texts) {
            String[] token = processor.getTokenFromText(text);
            Logger.debug("Retrieved " + token.length + " token from " + text.title);
        }
     }


    public static void main(String[] args) {
        Logger.setLogMode(LogMode.DEBUG);
        CorpusAnalyzer corpusAnalyzer = new CorpusAnalyzer();
        corpusAnalyzer.loadTexts();
    }
}
