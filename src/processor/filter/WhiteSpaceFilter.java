package processor.filter;

/**
 * Textfilter für die Verarbeitung einzelner Texte: Entfernt alle Leerzeichen am Anfang und Ende
 * des übergebenen Texts und kürzt Sequenzen aus mehreren Leerzeichen innerhalb des Texts auf
 * ein einzelnes Zeichen.
 */
public class WhiteSpaceFilter implements TextFilter {

    @Override
    public String filter(String text) {
        return text.trim().replaceAll(" +", " ");
    }

    @Override
    public String getFilterName() {
        return "WhiteSpaceFilter";
    }

}