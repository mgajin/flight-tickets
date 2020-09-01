package app.pagination;

import app.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public class PaginationResponse {

    private List<Ticket> model;
    private PageInfo pageInfo;

    protected void paginate() {
        int currentPage = pageInfo.getCurrentPage();
        int pageLimit = pageInfo.getLimit();

        boolean prev = currentPage > 1;
        boolean next = currentPage < pageInfo.getTotalPages();
        pageInfo.setPrevious(prev);
        pageInfo.setNext(next);

        int first = (currentPage - 1) * pageLimit;
        int end = first + pageLimit;
        int last = first;

        while (last < end) {
            try {
                this.model.get(last);
                last++;
            } catch (Exception e) {
                break;
            }
        }
        this.model = this.model.subList(first, last);
    }

    public List<Ticket> getModel() {
        if (this.pageInfo.getCurrentPage() > this.pageInfo.getTotalPages() || this.pageInfo.getCurrentPage() == 0) {
            return new ArrayList<>();
        }
        paginate();
        return model;
    }

    public void setModel(List<Ticket> model) {
        this.model = model;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}