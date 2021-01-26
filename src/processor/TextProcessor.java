package processor;

import debug.Logger;
import processor.filter.TextFilter;
import texts.Text;

import java.util.ArrayList;

public class TextProcessor {

    private ArrayList<TextFilter> currentFilters;

    public TextProcessor() {
        currentFilters = new ArrayList<>();
    }

    public void addFilter(TextFilter filter) {
        currentFilters.add(filter);
    }

    public String[] getTokenFromText(Text text) {
        Logger.debug("Filtering text from " + text.title);
        String result = text.raw;
        for(TextFilter filter: currentFilters) {
            result = filter.filter(result);
        }
        return result.split(" ");
    }

}
