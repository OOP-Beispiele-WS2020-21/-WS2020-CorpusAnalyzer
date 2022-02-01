package processor.filter;

public class PunctuationFilter implements TextFilter {
    private static final String[] PUNCTUATION_MARKS = {"\\.", "\\!", "\\?", "\\,", "\\;", "\\:"};

    @Override
    public String filter(String text) {
        String filteredText = text;
        for(String punctuationMark: PUNCTUATION_MARKS) {
            filteredText = filteredText.replaceAll(punctuationMark, "");
        }
        return filteredText;
    }

    @Override
    public String getFilterName() {
        return "PunctuationFilter";
    }

}
