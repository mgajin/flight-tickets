package app.pagination;

public class PageInfo {

    private static final int limit = 10;
    private int totalPages;
    private int currentPage;

    public int getLimit() {
        return limit;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}