package processor;

import processor.filter.TextFilter;
import texts.Text;

import java.util.ArrayList;

public class TextProcessor {

    private final ArrayList<TextFilter> filters;

    public TextProcessor() {
        filters = new ArrayList<>();
    }

    public void addFilter(TextFilter filter) {
        filters.add(filter);
    }

    public Text filterText(Text text) {
        Text filteredText = text;
        for (TextFilter filter : filters) {
            filteredText = filter.filter(filteredText);
        }
        return filteredText;
    }

}
