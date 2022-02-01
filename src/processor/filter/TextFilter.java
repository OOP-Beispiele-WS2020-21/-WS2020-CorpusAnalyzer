package processor.filter;

/**
 * Dieses Interface bildet die Grundlage für alle in der App verwendbaren Filter.
 * Alle Filter funktionieren dabei auf dieselbe Art und Weise: Der filter-Methode wird
 * ein Text übergeben, der Filter verarbeitet diesen intern und gibt die gefilterte
 * Fassung als neuen Text zurück. Alle Filter verfügen über eine Methode, über die
 * der Name bzw. die Bezeichnung des konkreten Filters zurückgegeben wird.
 */
public interface TextFilter {

    /**
     * Filtert den übergebenen Text anhand der spezifischen Kriterien des jeweiligen Filters
     * @param text Der zu filternde Originaltext
     * @return Die gefilterte Fassung des übergebenen Originaltexts
     */
    String filter(String text);

    /**
     * Gibt den Namen bzw. eine Bezeichnung des jeweiligen Filters zurück
     * @return Der Name des Filters
     */
    String getFilterName();
}
