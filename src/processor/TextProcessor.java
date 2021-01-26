package processor;

import processor.filter.TextFilter;
import texts.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class TextProcessor {

    private ArrayList<TextFilter> filters;

    public TextProcessor() {
        filters = new ArrayList<>();
    }

    public void addFilter(TextFilter filter) {
        filters.add(filter);
    }

    public String[] getTokenFromText(Text text) {
        String result = text.raw;
        for(TextFilter filter: filters) {
            result = filter.filter(result);
        }
        return result.split(" ");
    }
}
