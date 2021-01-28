package processor.filter;

import texts.Text;

public class PunctuationFilter implements TextFilter {

    private static final String[] PUNCTUATION_MARKS = {"\\.", "\\!", "\\?", "\\,", "\\;", "\"",};

    @Override
    public Text filter(Text text) {
        String filteredText = text.raw;
        for (String punctuationMark : PUNCTUATION_MARKS) {
            filteredText = filteredText.replaceAll(punctuationMark, " ");
        }
        return new Text(text.title, filteredText);
    }
}
