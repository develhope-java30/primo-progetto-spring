package com.example.primo_progetto_spring.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationAndSortingRequest {
    private int size;
    private int page;
    private boolean ascending;
    private String sortKey;

    public PaginationAndSortingRequest(int size, int page, boolean ascending,String sortKey) {
        this.size = size;
        this.page = page;
        this.ascending = ascending;
        this.sortKey = sortKey;
    }

  public Pageable toPageable() {
      Sort sort;

      if (sortKey == null){
         sort = Sort.unsorted();
      } else {
          if (ascending) {
              sort = Sort.by(sortKey);
          } else {
              sort = Sort.by(sortKey).descending();
          }
      }




        return PageRequest.of(page, size, sort);
  }
}
