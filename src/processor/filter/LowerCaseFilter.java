package processor.filter;

/**
 * Textfilter für die Verarbeitung einzelner Texte: Wandelt den übergebenen Text vollständig in
 * Kleinbuchstaben um.
 */
public class LowerCaseFilter implements TextFilter {

    @Override
    public String filter(String text) {
        return text.toLowerCase();
    }

    @Override
    public String getFilterName() {
        return "LowerCaseFilter";
    }

}
