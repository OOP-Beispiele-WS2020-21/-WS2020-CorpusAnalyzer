import config.AppConfig;
import debug.LogMode;
import debug.Logger;

public class CorpusAnalyzerApp {

    public static void main(String[] args) {
        Logger.setLogMode(LogMode.OFF);
        CorpusAnalyzer corpusAnalyzer = new CorpusAnalyzer();
        corpusAnalyzer.run(AppConfig.TEXT_FILE_DIRECTORY_PATH, AppConfig.OUTPUT_CUTOFF);
    }
}
