package danceschool.javaversion.filter;

import lombok.Data;

@Data
public class PaginationFilter {

  private int pageNumber;
  private int pageSize;

  public PaginationFilter() {
    this.pageNumber = 1;
    this.pageSize = 10;
  }

  public PaginationFilter(int pageNumber, int pageSize) {
    this.pageNumber = pageNumber < 1 ? 1 : pageNumber;
    this.pageSize = pageSize > 10 ? 10 : pageSize;
  }
}
