package processor.filter;

public interface TextFilter {

    String filter(String text);

    String getFilterName();
}
