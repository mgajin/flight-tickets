package app.pagination;

public class PageInfo {

    private static final int limit = 10;
    private int totalPages;
    private final int currentPage;

    public PageInfo(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}