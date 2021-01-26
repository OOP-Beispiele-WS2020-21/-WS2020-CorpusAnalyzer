package texts;

import debug.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        String fileName = textFile.getName();
        String title = fileName.substring(0, fileName.indexOf(FILE_NAME_TITLE_DELIMITER)).replaceAll("-", " ");
        StringBuilder rawTextBuilder = new StringBuilder();
        Scanner scanner = new Scanner(textFile);
        boolean firstLineReached = false;
        while (scanner.hasNext()) {
            String currentLine = scanner.nextLine();
            if (currentLine.equals("")) {
                firstLineReached = true;
                currentLine = scanner.next();
            }
            if (firstLineReached) {
                rawTextBuilder.append(currentLine);
            }
        }
        return new Text(title, fileName, rawTextBuilder.toString());
    }

}
