package processor.filter;

public class WhitespaceFilter implements TextFilter {


    @Override
    public String filter(String text) {
        // See: https://stackoverflow.com/questions/3958955/how-to-remove-duplicate-white-spaces-in-string-using-java/3958979
        return text.replaceAll("\\s+", " ");
    }

}
