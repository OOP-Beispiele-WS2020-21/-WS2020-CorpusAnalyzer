import config.AppConfig;
import debug.LogMode;
import debug.Logger;
import processor.TextProcessor;
import processor.filter.LowerCaseFilter;
import processor.filter.PunctuationFilter;
import processor.filter.StopwordFilter;
import processor.filter.WhitespaceFilter;
import texts.FolgerCorpusTextLoader;
import texts.Text;
import texts.Type;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CorpusAnalyzer {

    private ArrayList<Text> texts;

    public void loadTexts() {
        File textFileDirectory = new File(AppConfig.TEXT_FILE_DIRECTORY_PATH);
        texts = FolgerCorpusTextLoader.loadFrom(textFileDirectory);
    }

    public void printSortedTypeList(int cutOff) {
        ArrayList<Type> types = getSortedTypeList(texts);
        Logger.debug("Printing result list");
        System.out.printf("Most frequent types used by Shakesoeare (Folger Corpus with %d plays, poems and sonets) \n", texts.size());
        System.out.printf("%5s | %-20s | %s \n", "Pos", "Type", "Frequency");
        for (int i = 0; i < cutOff; i++) {
            System.out.printf("%5d | %-20s | %d tokens \n", i + 1, types.get(i).value, types.get(i).getCount());
        }
    }

    private ArrayList<Type> getSortedTypeList(ArrayList<Text> texts) {
        HashMap<String, Type> typesMap = new HashMap<>();
        TextProcessor processor = new TextProcessor();
        processor.addFilter(new PunctuationFilter());
        processor.addFilter(new LowerCaseFilter());
        processor.addFilter(new StopwordFilter());
        processor.addFilter(new WhitespaceFilter());
        for (Text text : texts) {
            Logger.debug("Get tokens from " + text.title);
            String[] tokens = processor.getTokenFromText(text);
            for (String token : tokens) {
                Type type = typesMap.get(token);
                if (type == null) {
                    Logger.debug("Adding new token " + token);
                    type = new Type(token);
                }
                type.increaseCount();
                typesMap.put(token, type);
            }
        }
        ArrayList<Type> types = new ArrayList<>(typesMap.values());
        types.sort(Collections.reverseOrder());
        return types;
    }


    public static void main(String[] args) {
        Logger.setLogMode(LogMode.DEBUG);
        CorpusAnalyzer corpusAnalyzer = new CorpusAnalyzer();
        corpusAnalyzer.loadTexts();
        corpusAnalyzer.printSortedTypeList(AppConfig.OUTPUT_CUTOFF); // Limit output to 10 most frequent types
    }
}
