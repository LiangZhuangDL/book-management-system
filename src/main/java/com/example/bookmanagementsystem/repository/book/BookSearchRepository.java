package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.book.BookSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface BookSearchRepository extends ElasticsearchCrudRepository<BookSearch, String> {

    Page<BookSearch> findBookSearchByTitleContaining(String title, Pageable pageable);

    Page<BookSearch> findBookSearchByAuthorContaining(String author, Pageable pageable);

    Page<BookSearch> findBookSearchByPublishingHouseContaining(String publishingHouse, Pageable pageable);

    Page<BookSearch> findBookSearchByIsbnContaining(String isbn, Pageable pageable);
}
