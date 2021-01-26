package debug;

import java.io.PrintStream;

public enum LogMode {

    OFF("", null, null),
    ERROR("Error", System.err, LogColor.ANSI_RED),
    WARNING("Warning", System.out, LogColor.ANSI_YELLOW),
    DEBUG("Debug", System.out, LogColor.ANSI_GREEN);

    public final String label;
    public final PrintStream stream;
    public final LogColor color;

    LogMode(String label, PrintStream stream, LogColor color) {
        this.label = label;
        this.stream = stream;
        this.color = color;
    }

}
