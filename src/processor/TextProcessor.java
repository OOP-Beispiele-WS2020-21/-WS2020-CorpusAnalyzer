package processor;

import debug.Logger;
import processor.filter.TextFilter;
import texts.Text;
import texts.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Mit Instanzen dieser Klasse können einzelne Texte und Tokensammlungen verarbeitet werden.
 */
public class TextProcessor {

    private ArrayList<TextFilter> currentFilters; // Liste der aktuell verwendeten Filter

    public TextProcessor() {
        currentFilters = new ArrayList<>();
    }

    /**
     * Fügt dem Prozessor einen weiteren Filter hinzu, der im Anschluss bei jeder Token-Extraktion
     * verwendet wird. Alle Filter werden in der Reihenfolge angewendet, in der sie an den
     * Prozessor übergeben wurden.
     * @param filter Der Filter, der bei den folgenden Extraktionsvorgängen verwendet werden soll
     */
    public void addFilter(TextFilter filter) {
        currentFilters.add(filter);
    }

    /**
     * Gibt eine Liste aller Token (durch Leerzeichen getrennte Wörter) im übergebenen Text
     * zurück. Falls im Vorfeld Textfilter zum Prozessor hinzugefügt wurden, werden dieser vor
     * der Extraktion der Token angewendet.
     *
     * @param text Der Text, aus dem die Token extrahiert werden sollen
     * @return Eine Liste aller Token im übergebenen Text
     */
    public String[] getTokenFromText(Text text) {
        Logger.debug("Filtering text from " + text.title);
        // Wir Lesen den zu verarbeitenden Textinhalt aus ...
        String result = text.raw;
        for(TextFilter filter: currentFilters) {
            Logger.debug("Applying " + filter.getFilterName());
            // ... und wenden alle bekannten Filter an. Als Input dient dabei immer das Ergebnis des vorherigen Schrittes.
            result = filter.filter(result);
        }
        // Die Token werden erstellt, in dem der vollständig gefiltertet Text an den Leerzeichen aufgetrennt wird.
        return result.split(" ");
    }

    /**
     * Gibt eine Liste alle Types zurück, die in dem übergebenen Array aus Token enthalten sind. Für jeden Type
     * wird dabei auch dessen Häufigkeit innerhalb der Tokenliste festgestellt.
     * @param tokens Die Liste der zu analysierenden Token
     * @return Die Liste aller individuellen Typen inkl. deren Häufigkeit innerhalb der Tokenliste
     */
    public Type[] getTypesFromTokens(String[] tokens) {
        // Innerhalb der Methode speichern wir alle Typen in einer HashMap, um über deren Schlüssel (Text des Typen) schnell auf jeden Typen zugreifen zu können
        HashMap<String, Type> typeMap = new HashMap<>();
        // Wir iterieren über alle Token ...
        for(String token: tokens) {
            // ... ignorieren möglicherweise vorhandene, leere Strings ...
            if(token.isEmpty()) {
                continue;
            }
            // ... und prüfen, ob der Type zu diesem Token bereits in der HashMap vorhanden ist.
            Type currentType = typeMap.get(token);
            // Fall noch kein passender Type in der HashMap abgelegt wurde ...
            if(currentType == null) {
                // ... erstellen wir diesen ...
                currentType = new Type(token);
                // ... und speichern ihn, mit dem Textinhalt als Schlüssel, in der HashMap ab
                typeMap.put(token, currentType);
            }
            /*
             * In der lokalen Variable currentType ist nun IMMER entweder der aus der HashMap ausgelesene
             * Type oder der soeben neu erstellte. In beiden Fällen können wir dessen Auftretenshäufigkeit jetzt
             * einfach inkrementieren.
             */
            currentType.increaseFrequency();
        }
        // Nach dem prüfen aller Token lesen wir die Werte der HashMap als ArrayList aus ...
        ArrayList<Type> types = new ArrayList<>(typeMap.values());
        // ... und sortieren die Typen nach ihrer Auftretenshäufigkeit ...
        types.sort(Collections.reverseOrder());
        // ... und geben diese sortierte Liste als neues Array zurück.
        return types.toArray(new Type[0]);
    }

}
