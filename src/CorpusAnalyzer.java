import config.AppConfig;
import debug.Logger;
import processor.TextProcessor;
import processor.filter.LowerCaseFilter;
import processor.filter.PunctuationFilter;
import processor.filter.StopWordFilter;
import processor.filter.WhitespaceFilter;
import texts.FolgerCorpusTextLoader;
import texts.Text;
import texts.Type;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CorpusAnalyzer {


    public void run(String corpusDirectoryPath, int outPutCutoff) {
        ArrayList<Text> texts = loadTexts(corpusDirectoryPath);
        String[] tokens = collectTokensFromText(texts);
        Type[] types = getTypesFromTokens(tokens);
        printSortedTypeList(types, outPutCutoff, texts.size()); // Limit output to 10 most frequent types
    }

    private ArrayList<Text> loadTexts(String corpusDirectoryPath) {
        File textFileDirectory = new File(corpusDirectoryPath);
        return FolgerCorpusTextLoader.loadFrom(textFileDirectory);
    }

    private String[] collectTokensFromText(ArrayList<Text> texts) {
        ArrayList<String> tokens = new ArrayList<>();
        TextProcessor processor = new TextProcessor();
        processor.addFilter(new PunctuationFilter());
        processor.addFilter(new LowerCaseFilter());
        processor.addFilter(new StopWordFilter(new File(AppConfig.STOP_WORD_FILE_PATH)));
        processor.addFilter(new WhitespaceFilter());
        for (Text text : texts) {
            String[] tokensFromText = processor.filterText(text).tokenize();
            Collections.addAll(tokens, tokensFromText);
        }
        return tokens.toArray(new String[0]);
    }

    private Type[] getTypesFromTokens(String[] tokens) {
        HashMap<String, Type> typesMap = new HashMap<>();
        for (String token : tokens) {
            Type type = typesMap.get(token);
            if (type == null) {
                Logger.debug("Adding new token " + token);
                type = new Type(token);
            }
            type.increaseCount(tokens.length);
            typesMap.put(token, type);
        }
        ArrayList<Type> types = new ArrayList<>(typesMap.values());
        types.sort(Collections.reverseOrder());
        return types.toArray(new Type[0]);
    }

    private void printSortedTypeList(Type[] types, int cutOff, int numberOfTexts) {
        Logger.debug("Printing result list");
        System.out.printf("Most frequent words used by Shakespeare (Folger Corpus with %d plays, poems and sonnets) \n", numberOfTexts);
        System.out.printf("%5s | %-20s | %-9s | %-5s |\n", "Pos", "Type", "Frequency", "Ratio");
        for (int i = 0; i < cutOff; i++) {
            System.out.printf("%5d | %-20s | %9d | %4.2f%% |\n", i + 1, types[i].value, types[i].getFrequency(), types[i].getRelativeFrequency());
        }
    }

}
