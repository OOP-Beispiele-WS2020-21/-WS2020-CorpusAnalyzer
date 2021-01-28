package processor.filter;

import texts.Text;

public class WhitespaceFilter implements TextFilter {
    
    @Override
    public Text filter(Text text) {
        // See: https://stackoverflow.com/questions/3958955/how-to-remove-duplicate-white-spaces-in-string-using-java/3958979
        String filteredText = text.raw.replaceAll("\\s+", " ");
        return new Text(text.title, filteredText);
    }

}
