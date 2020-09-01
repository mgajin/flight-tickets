package app.pagination;

public class PageInfo {

    private static final int limit = 10;
    private int totalPages;
    private final int currentPage;
    private boolean previous;
    private boolean next;

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

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}