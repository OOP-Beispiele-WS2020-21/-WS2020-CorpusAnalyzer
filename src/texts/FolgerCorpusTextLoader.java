package texts;

import debug.Logger;
import utils.FileLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FolgerCorpusTextLoader {

    private static final String TXT_FILE_EXTENSION = ".txt";
    private static final String FILE_NAME_TITLE_DELIMITER = "_TXT_";

    public static ArrayList<Text> loadFrom(File textFileDirectory) {
        Logger.debug("Reading files from: " + textFileDirectory);
        ArrayList<Text> texts = new ArrayList<>();
        File[] allTextFiles = textFileDirectory.listFiles();
        for (File textFile : allTextFiles) {
            if (!textFile.isFile()) {
                continue;
            }
            if (!textFile.getName().endsWith(TXT_FILE_EXTENSION)) {
                continue;
            }
            Text currentText = null;
            try {
                currentText = FolgerCorpusTextLoader.loadTextFromFile(textFile);
                texts.add(currentText);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return texts;
    }

    private static Text loadTextFromFile(File textFile) throws FileNotFoundException {
        Logger.debug("Loading text from file: " + textFile.getName());
        String title = textFile.getName().substring(0, textFile.getName().indexOf(FILE_NAME_TITLE_DELIMITER)).replaceAll("-", " ");
        String content = FileLoader.loadFullTextFromFile(textFile);
        return new Text(title, content);
    }

}
