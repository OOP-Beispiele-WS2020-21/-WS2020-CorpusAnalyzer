import config.AppConfig;
import debug.LogMode;
import debug.LogTarget;
import debug.Logger;
import processor.TextProcessor;
import processor.filter.LowerCaseFilter;
import processor.filter.PunctuationFilter;
import processor.filter.StopWordFilter;
import processor.filter.WhiteSpaceFilter;
import texts.FolgerCorpusTextLoader;
import texts.Text;
import texts.Type;

import java.io.File;
import java.util.*;

public class CorpusAnalyzer {

    public void run() {
        Logger.debug("Starting CorpusAnalyzer ...");
        File textFileDirectory = new File(AppConfig.TEXT_FILE_DIRECTORY_PATH);
        ArrayList<Text> texts = FolgerCorpusTextLoader.loadFromDirectory(textFileDirectory);
        TextProcessor processor = new TextProcessor();
        processor.addFilter(new LowerCaseFilter());
        processor.addFilter(new PunctuationFilter());
        processor.addFilter(new StopWordFilter());
        ArrayList<String> tokens = new ArrayList<>();
        for(Text text: texts) {
            String[] token = processor.getTokenFromText(text);
            Logger.debug("Retrieved " + token.length + " token from " + text.title);
            tokens.addAll(List.of(token));
        }
        Type[] types = processor.getTypesFromTokens(tokens.toArray(new String[0]));
        printTypeList(types);
     }

     private void printTypeList(Type[] types) {
        System.out.println("Most frequent words used by Shakespeare");
        System.out.printf("%5s | %20s | %s \n", "Pos", "Type", "Frequency" ); // Tabellenkopf
         for(int i = 0; i < AppConfig.OUTPUT_CUTOFF; i++) {
             System.out.printf("%5d | %20s | %d \n", i + 1, types[i].value, types[i].getFrequency());
         }
     }

    public static void main(String[] args) {
        Logger.setLogMode(LogMode.DEBUG);
        Logger.setLogTarget(LogTarget.CONSOLE);
        CorpusAnalyzer corpusAnalyzer = new CorpusAnalyzer();
        corpusAnalyzer.run();
    }
}
