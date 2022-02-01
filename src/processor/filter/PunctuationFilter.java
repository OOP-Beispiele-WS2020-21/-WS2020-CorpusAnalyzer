package processor.filter;

/**
 * Textfilter für die Verarbeitung einzelner Texte: Entfernt alle im übergebenen Text auftretende
 * Satzzeichen. Die Liste der relevanten Satzzeichen wird hier über ein String-Array direkt in der Klasse definiert.
 */
public class PunctuationFilter implements TextFilter {
    // Eine Satzzeichen können auch Operatoren in einem regulären Ausdruck sein und werden daher für den Einsatz mit den "replace"-Funktionen bereits hier "escapet"
    private static final String[] PUNCTUATION_MARKS = {"\\.", "\\!", "\\?", "\\,", "\\;", "\\:"};

    @Override
    public String filter(String text) {
        String filteredText = text;
        for(String punctuationMark: PUNCTUATION_MARKS) {
            filteredText = filteredText.replaceAll(punctuationMark, "");
        }
        return filteredText;
    }

    @Override
    public String getFilterName() {
        return "PunctuationFilter";
    }

}
