package texts;

import debug.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FolgerCorpusTextLoader {

    private static final String TXT_FILE_EXTENSION = ".txt";

    public static ArrayList<Text> loadFromDirectory(File textFileDirectory) {
        Logger.debug("Reading files from " + textFileDirectory.getPath());
        ArrayList<Text> texts = new ArrayList<>();
        File[] allTextFiles = textFileDirectory.listFiles();
        for (File textFile : allTextFiles) {
            if(!textFile.isFile()) {
                continue;
            }
            if(!textFile.getName().endsWith(TXT_FILE_EXTENSION)) {
                continue;
            }
            try {
                Text currentText = loadTextFromFile(textFile);
                texts.add(currentText);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return texts;
    }

    private static Text loadTextFromFile(File textFile) throws FileNotFoundException {
        Logger.debug("Loading text from file " + textFile.getName());
        String fileName = textFile.getName();
        String title = fileName.substring(0, fileName.indexOf("_TXT_"));
        StringBuilder rawTextBuilder = new StringBuilder();
        Scanner scanner = new Scanner(textFile);
        while(scanner.hasNext()) {
            rawTextBuilder.append(scanner.nextLine());
        }
        return new Text(title, fileName, rawTextBuilder.toString());
    }

}
