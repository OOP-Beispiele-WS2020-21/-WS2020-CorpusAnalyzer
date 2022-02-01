package processor.filter;

public class WhiteSpaceFilter implements TextFilter {

    @Override
    public String filter(String text) {
        return text.trim().replaceAll(" +", " ");
    }

    @Override
    public String getFilterName() {
        return "WhiteSpaceFilter";
    }

}
