package debug;

// Color values taken from https://stackoverflow.com/a/5762502
public enum LogColor {
    ANSI_RESET("\u001B[0m"), // Will reset changed color in output
    ANSI_DEFAULT(""), // Empty color string to NOT change default output color
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m");

    public final String value;

    LogColor(String value) {
        this.value = value;
    }

}
