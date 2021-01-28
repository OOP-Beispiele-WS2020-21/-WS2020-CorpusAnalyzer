package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {

    public static String[] loadLinesFromText(File file) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());

        }
        return lines.toArray(new String[0]);
    }

    public static String loadFullTextFromFile(File file) throws FileNotFoundException {
        StringBuilder rawTextBuilder = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String currentLine = scanner.nextLine();
            rawTextBuilder.append(currentLine);

        }
        return rawTextBuilder.toString();
    }
}
