package processor;

import debug.Logger;
import processor.filter.TextFilter;
import texts.Text;
import texts.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
            Logger.debug("Applying " + filter.getFilterName());
            result = filter.filter(result);
        }
        return result.split(" ");
    }

    public Type[] getTypesFromTokens(String[] tokens) {
        HashMap<String, Type> typeMap = new HashMap<>();
        for(String token: tokens) {
            if(token.isEmpty()) {
                continue;
            }
            Type currentType = typeMap.get(token);
            if(currentType == null) {
                currentType = new Type(token);
                typeMap.put(token, currentType);
            }
            currentType.increaseFrequency();
        }
        ArrayList<Type> types = new ArrayList<>(typeMap.values());
        types.sort(Collections.reverseOrder());
        return types.toArray(new Type[0]);
    }

}
