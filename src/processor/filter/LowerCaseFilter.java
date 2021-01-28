package processor.filter;

import texts.Text;

public class LowerCaseFilter implements TextFilter {

    @Override
    public Text filter(Text text) {
        String filteredText = text.raw.toLowerCase();
        return new Text(text.title, filteredText);
    }

}
