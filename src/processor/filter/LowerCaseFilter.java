package processor.filter;

public class LowerCaseFilter implements TextFilter {
    @Override
    public String filter(String text) {
        return text.toLowerCase();
    }
}
